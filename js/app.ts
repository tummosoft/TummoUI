var working: boolean = false;

function hoverEffect(e) {
    if (working == false) {
        var width = $(e).width();
        var size = 0;
        var ele = $(e).find(".hover-effect");
        var id;
        ele.show();
        working = true;
        id = setInterval(frame, 10);
        function frame() {
            size = size + 0.9;
            ele.css("width", size + "px");
            ele.css("height", size + "px");
            if (size > 50) {
                clearInterval(id);
                working = false;
                ele.hide();        
            }
        }    
}}