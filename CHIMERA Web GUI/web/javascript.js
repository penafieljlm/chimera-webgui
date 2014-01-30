
//Get value from an input field
function getFieldValue(fieldId) { 
    // 'get field' is part of Semantics form behavior API
    return $('.ui.form').form('get field', fieldId).val();
}

function submitForm() {
    var formData = {
        field1: getFieldValue('someId')
    };

    $.ajax({ type: 'POST', url: '/api/someRestEndpoint', data: formData, success: onFormSubmitted });
}

// Handle post response
function onFormSubmitted(response) {
    // Do something with response ...
}

$(document).ready(
    function(){
        $('.message .close').on('click', function() {
            $(this).closest('.message').fadeOut();
        });
        
        $('.ui.checkbox')
            .checkbox()
        ;
        
        $('#adduser').on('click', function() {
            $('.ui.sidebar')
                .sidebar({
                    overlay: true
                  })
                .sidebar('toggle')
            ;
        });
        
        $('.ui.dropdown')
          .dropdown()
        ;
        
        $('#cshowoutputmessages').on('click', function() {
            $('#cside')
                .sidebar({
                    overlay: true
                  })
                .sidebar('toggle')
            ;
        });
        
        $('#dshowoutputmessages').on('click', function() {
            $('#dside')
                .sidebar({
                    overlay: true
                  })
                .sidebar('toggle')
            ;
        });
        
        $('#newuser').on('click', function() {
            $('#uside')
                .sidebar({
                    overlay: true
                  })
                .sidebar('toggle')
            ;
        });
        
        $(".sticky").sticky({topSpacing:30});
        
        
});
