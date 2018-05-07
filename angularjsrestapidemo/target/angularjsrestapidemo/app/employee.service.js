angular.module('crudApp').factory('Employee', Employee);

Employee.$inject = [ '$resource' ];

function Employee($resource) {
	var resourceUrl = 'api/employee/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}