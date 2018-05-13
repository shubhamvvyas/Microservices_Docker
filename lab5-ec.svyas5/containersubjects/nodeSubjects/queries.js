var promise = require('bluebird');
var ip = require('ip');

var options = {
	promiseLib: promise
};

var pgp = require('pg-promise')(options);
var connectionString = 'postgres://ser422:ser422@'+ip.address()+':5432/lab5';
var db = pgp(connectionString);

function getAllSubjects(req, res, next) {
	  db.any('SELECT distinct subject from course')
	    .then(function (subjectList) {
		          res.status(200)
		            .json({
				    subjectList
				            });
		        })
	    .catch(function (err) {
		          return next(err);
		        });
}

module.exports = {
	  getAllSubjects: getAllSubjects
};
