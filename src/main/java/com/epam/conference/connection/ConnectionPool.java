package com.epam.conference.connection;

import com.epam.conference.connection.exception.ConnectionPoolException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final static Lock LOCK = new ReentrantLock();
    private static ConnectionPool instance;

    private Queue<ProxyConnection> availableConnections;
    private Queue<ProxyConnection> connectionsInUse;

    private Semaphore connectionsSemaphore;
    private final Lock connectionsLock = new ReentrantLock();

    private ConnectionPool() {

    }

    public static ConnectionPool getInstance() {
        ConnectionPool pool = instance;
        if (pool == null) {
            try {
                LOCK.lock();
                pool = instance;
                if (pool == null) {
                    pool = new ConnectionPool();
                    pool.init();
                    instance = pool;
                }
            } finally {
                LOCK.unlock();
            }
        }

        return instance;
    }

    private void init() {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream("database.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            String poolSize = properties.getProperty("db.poolSize");
            int poolSizeValue = Integer.parseInt(poolSize);
            this.initConnections(poolSizeValue);
        } catch (IOException e) {
            throw new ConnectionPoolException(e);
        }
    }

    private void initConnections(int connectionCount) {
        this.availableConnections = new ArrayDeque<>(connectionCount);
        this.connectionsInUse = new ArrayDeque<>(connectionCount);
        this.connectionsSemaphore = new Semaphore(connectionCount, true);

        ConnectionFactory factory = new ConnectionFactory();
        try {
            for (int i = 0; i < connectionCount; i++) {
                Queue<ProxyConnection> availableConnections = this.availableConnections;
                ProxyConnection proxyConnection = new ProxyConnection(factory.create(), this);
                availableConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
                connectionsSemaphore.release();
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection;

        try {
            connectionsSemaphore.acquire();
            connectionsLock.lock();
            connection = availableConnections.poll();
            connectionsInUse.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        } finally {
            connectionsLock.unlock();
        }

        return connection;
    }

    public void shutDown() {
        try {
            for (ProxyConnection connection : availableConnections) {
                connection.close();
            }
            for (ProxyConnection connection : connectionsInUse) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

}