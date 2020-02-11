let sectionsCount;

$(document).ready(() => {
    registerAddSectionButtonListener();
    sectionsCount = 1;
});

function registerAddSectionButtonListener() {
    $('#add-section-button').on('click', function () {
        sectionsCount++;
        let textarea = document.createElement('textarea');
        textarea.setAttribute('name', 'section-topic[]');
        textarea.setAttribute('type', 'text');
        textarea.setAttribute('maxlength', '50');
        textarea.setAttribute('placeholder', sectionsCount);
        textarea.setAttribute('required', 'true');
        $(textarea).addClass('form-control');

        $('#sections-div').append(textarea);
    })
}