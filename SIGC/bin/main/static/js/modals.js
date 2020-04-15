$(document).ready(function() {

	$('.login').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href, function() {
			$('#exampleModalCenter').modal('toggle');
		});
	});

	$('.table .dBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delHref').attr('href', href);
		$('#deleteModal').modal();
	});
	

});