function dragElement(element) {
    if ($(element).hasClass('drag-over') == false) {
        $(element).addClass('drag-over');
    }
    
    $('.drag-over').on("mousedown", function(e) {
        var posX = $(this).offset().left,
            posY = $(this).offset().top,
            mouseX = e.pageX,
            mouseY = e.pageY;
        $(document).on("mouseup", function() {
          $(document).off("mousemove");
        });
        $(document).on("mousemove", function(e) {
          $('.drag-over').offset({
            left: posX + e.pageX - mouseX,
            top: posY + e.pageY - mouseY
          });
        });
      });
}