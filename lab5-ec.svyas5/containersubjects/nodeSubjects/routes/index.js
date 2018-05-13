var express = require('express');
var router = express.Router();

var db = require('../queries');
/* GET home page. */
router.get('/api/subjects', db.getAllSubjects);

module.exports = router;
