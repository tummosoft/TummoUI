let working = false;    

function btnHoverEffect(e) {              
    if (working == false) { 
        var checkEnable = $(e).hasClass('disabled');        
        if (checkEnable == false) {
            hoverEffect(e);
        }     
    }
}

function checkButton(e) {
    var rs = 70;
    if ($(e).hasClass('tummo-btn-round')) {
        rs = 20;
    }
    return rs;
}

function hoverEffect(e) {
    var ss = checkButton(e);
    var size = 0;
    var ele = $(e).find(".hover-effect");
        var id;
        ele.show();
        var width = $(e).width();
        id = setInterval(frame, 5);
        function frame() {
            size = size + 0.8;
            ele.css("width", size + "px");
            ele.css("height", size + "px");
            if (size > ss) {
                clearInterval(id);                    
                ele.hide();                    
                $(e).addClass("on-hover");
            }
        }
        working = true;     
}

function quickMenuHideEffect(e) {
        removeEffect(e);        
    }
      
    function removeEffect(e) {
        var ele = [];
        var success = false;
        var chk = checkButton(e);
        if (chk == 70) {
            ele = $('.tummo-btn');
        } else {
            ele = $('.tummo-btn-round');
        }
         
         for (var i=0; i < ele.length; i++) {
            $(ele[i]).removeClass("on-hover");
            success = true;            
        }
        if (success == true) {
            working = false;
        }
    }   