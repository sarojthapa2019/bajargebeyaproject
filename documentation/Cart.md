#Cart.js:
##Manage the Cart as a whole and  number of items in the Cart: 


	function initSvg()
	{
		if($('img.svg').length)
		{
			jQuery('img.svg').each(function()
			{
				var $img = jQuery(this);
				var imgID = $img.attr('id');
				var imgClass = $img.attr('class');
				var imgURL = $img.attr('src');


				jQuery.get(imgURL, function(data)
				{
					// Get the SVG tag, ignore the rest
					var $svg = jQuery(data).find('svg');


					// Add replaced image's ID to the new SVG
					if(typeof imgID !== 'undefined') {
					$svg = $svg.attr('id', imgID);
					}
					// Add replaced image's classes to the new SVG
					if(typeof imgClass !== 'undefined') {
					$svg = $svg.attr('class', imgClass+' replaced-svg');
					}


					// Remove any invalid XML tags as per http://validator.w3.org
					$svg = $svg.removeAttr('xmlns:a');


					// Replace image with new SVG
					$img.replaceWith($svg);
				}, 'xml');
			});
		}	
	}
	
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTEzMzQ2NTY3MTQsMTk4MDU2NDU3MF19
-->