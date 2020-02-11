$(document).ready(() => {
    registerLocalButtonListener();
});

function registerLocalButtonListener() {
    $('#local-button').on('click', function () {
        let localButton = document.getElementById('local-button');
        let localDiv = document.getElementById('localCollapse');
        if ($(localButton).hasClass('collapsed')) {
            $(localDiv).removeClass('show');
            $(localButton).removeClass('collapsed');
        } else {
            $(localDiv).addClass('show');
            $(localButton).addClass('collapsed');
        }
    })
}