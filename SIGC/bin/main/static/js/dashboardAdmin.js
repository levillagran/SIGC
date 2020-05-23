$(document).ready(function() {

	$('.tTypeQ').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargar").load(href);
	});

	$('.tSect').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargar").load(href);
	});

	$('.tQuest').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargar").load(href);
	});

	$('.tAdmin').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargar").load(href);
	});

	$('.tBackOfc').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargar").load(href);
	});

	$('.tAudi').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargar").load(href);
	});
	
	$('.tCust').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$("#seccionRecargar").load(href);
	});

	$('.tRole').on('click', function(event) {
		event.preventDefault();
		$("#seccionRecargar").load("tableRole");
	});

	$('.tAssig').on('click', function(event) {
		event.preventDefault();
		$("#seccionRecargar").load("tableAssig");
	});

	$('.tSche').on('click', function(event) {
		event.preventDefault();
		$("#seccionRecargar").load("tableSche");
	});

	$('.nBtn, .eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('.modal-body').load(href, function() {
			$('.myForm #exampleModal').modal('toggle');
		});
	});

	$('.table .dBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delHref').attr('href', href);
		$('#deleteModal').modal();
	});

});