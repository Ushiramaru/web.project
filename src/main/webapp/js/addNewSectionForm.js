let sectionsCount;

$(document).ready(() => {
    registerAddSectionButtonListener();
    sectionsCount = 1;
});

function registerAddSectionButtonListener() {
    $('#add-section-button').on('click', function () {
        sectionsCount++;
        let label = document.createElement('label');
        let input = document.createElement('input');
        input.setAttribute('name', 'section-topic[]');
        input.setAttribute('type', 'text');
        label.innerText = 'Section ' + sectionsCount;
        label.appendChild(input);
        $('#sections-div').append(label);
    })
}