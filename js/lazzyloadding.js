// JavaScript code to implement lazy loading
$(document).ready(function() {
    var currentPage = 0;
    var limit = 10;
    
    // Function to retrieve data from server
    function loadData() {
      $.ajax({
        url: 'your-server-url',
        data: {
          page: currentPage,
          limit: limit
        },
        success: function(data) {
          // Append data to table body
          $.each(data, function(index, item) {
            $('#table-body').append(
              '<tr>' +
                '<td>' + item.name + '</td>' +
                '<td>' + item.age + '</td>' +
                '<td>' + item.city + '</td>' +
              '</tr>'
            );
          });
        }
      });
    }
    
    // Load initial data
    loadData();
    
    // Attach event handler to window scroll event
    $(window).scroll(function() {
      // Check if user has scrolled to bottom of page
      if ($(window).scrollTop() + $(window).height() >= $(document).height()) {
        currentPage++;
        loadData();
      }
    });
  });