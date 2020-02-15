let sectionsCount;

$(document).ready(() => {
    registerAddSectionButtonListener();
    sectionsCount = 1;
});

function registerAddSectionButtonListener() {
    $('#add-section-button').on('click', function () {
        sectionsCount++;
        let label = document.getElementById('new-section-label');
        let labelText = label.textContent;
        let newLabel = document.createElement('label');
        newLabel.setAttribute('for', 'section-topic-' + sectionsCount);
        newLabel.textContent = labelText.substring(0, labelText.indexOf(' ', 0) + 1).concat(sectionsCount);

        let textarea = document.createElement('textarea');
        textarea.setAttribute('name', 'section-topic[]');
        textarea.setAttribute('type', 'text');
        textarea.setAttribute('maxlength', '50');
        textarea.setAttribute('placeholder', sectionsCount);
        textarea.setAttribute('required', 'true');
        textarea.setAttribute('id', 'section-topic-' + sectionsCount);
        $(textarea).addClass('form-control');

        $('#sections-div').append(newLabel).append(textarea);
    })
}