$(document).ready(() => {
    registerNavbarButtonListener();
});

function registerNavbarButtonListener() {
    $('#navbar-button').on('click', function () {
        let navbarButton = document.getElementById('navbar-button');
        let navbarDiv = document.getElementById('navbarCollapse');
        if ($(navbarButton).hasClass('collapsed')) {
            $(navbarDiv).removeClass('show');
            $(navbarButton).removeClass('collapsed');
        } else {
            $(navbarDiv).addClass('show');
            $(navbarButton).addClass('collapsed');
        }
    })
}