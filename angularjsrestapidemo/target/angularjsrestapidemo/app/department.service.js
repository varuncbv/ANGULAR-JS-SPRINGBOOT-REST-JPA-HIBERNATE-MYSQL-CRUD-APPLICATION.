(function() {
	'use strict';
	angular.module('crudApp').factory('Department', Department);

	Department.$inject = [ '$resource' ];

	function Department($resource) {
		var resourceUrl = 'api/department/:id';

		return $resource(resourceUrl, {}, {
			'update' : {
				method : 'PUT'
			}
		});
	}
})();
