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
        
        $('#dgshowoutputmessages').on('click', function() {
            $('#dgside')
                .sidebar({
                    overlay: true
                  })
                .sidebar('toggle')
            ;
        });
        
        $('#tshowoutputmessages').on('click', function() {
            $('#tside')
                .sidebar({
                    overlay: true
                  })
                .sidebar('toggle')
            ;
        });
        
        $('#pshowoutputmessages').on('click', function() {
            $('#pside')
                .sidebar({
                    overlay: true
                  })
                .sidebar('toggle')
            ;
        });
        
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
