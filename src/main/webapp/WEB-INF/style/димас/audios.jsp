<html>
<head>
    <title>Audio</title>
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="table.css">
    <link rel="stylesheet" href="search_input.css">
</head>
<body>
<header>
    <h1>xui</h1>
</header>

<div class="main">

    <div class="navbar-menu">
        <nav>
            <ul>
                <li>
                    <a href="<c:url value="/controller?command=main"/>">Home</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=showUsers"/>">Users</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=audios"/>">Audios</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=showUsers"/>">Albums</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=showUsers"/>">Orders</a>
                </li>
            </ul>
        </nav>
    </div>

    <div class="container">
        <div class="search">
            <form action="Controller" method="post">
                <div class="search-input">
                    <label for="search-input"></label>
                    <input class="form-control" type="text" name="input" id="search-input"
                           placeholder="Type name of track or artist...">
                </div>

                <div class="submit-btn">
                    <button type="submit" name="command" value="showAudios">Search</button>
                </div>
            </form>
        </div>

        <div class="buttons">
            <div class="add-track">
                <form method="post" action="Controller">
                    <input type="submit" value="audio" name="command">
                </form>
            </div>

            <div class="add-artist">
                <form method="post" action="Controller">
                    <input type="submit" value="showAddArtist" name="command">
                </form>
            </div>
        </div>


        <div class="tracks">
            <table>
                <thead>
                <tr>
                    <th scope="col">Title</th>
                    <th scope="col">Artist</th>
                    <th scope="col">Price</th>
                    <th scope="col">Genre</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${audioByTitle.getTitle()}</td>
                    <td>${audioByTitle.getArtistId()}</td>
                    <td>${audioByTitle.getPrice()}</td>
                    <td>${audioByTitle.getGenre()}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
