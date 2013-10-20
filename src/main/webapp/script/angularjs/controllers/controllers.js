/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function HelloController($scope) {
    $scope.greeting = {text: 'Hello'};
}

function QualController($scope, $location, $http) {
    var debug = true;
    var thisUrl = document.URL.toString();
    var idComp = thisUrl.split("?")[1];
    $scope.qual_id = idComp.split("=")[1];
    console.log("id: " + $scope.qual_id);
    var qualUrl = rootContextUrl + "/crud/qual";
    //enity urls
    $scope.qualLineUrl = rootContextUrl + "/crud/qual-line";
    $scope.readEntityUrl = qualUrl + "/read" + "?" + idComp;
    $scope.updateEntityUrl = qualUrl + "/update" + "?" + idComp;
    $scope.deleteEntityUrl = qualUrl + "/delete" + "?" + idComp;
    $scope.getLinesUrl = rootContextUrl + "/qual" + "/lines-by-id" + "?" + idComp;
    //line specific urls
    $scope.delQualLineUrl = $scope.qualLineUrl + "/delete";
    $scope.addQualLineUrl = $scope.qualLineUrl + "/add";
    //$scope.updateQualLineUrl = $scope.qualLineUrl + "/update";
    //TODO: create a "QualLine" object and create new instances in the lines and addLine function


    $scope.name = "test qual name";
    $scope.role = "test qual role";
    $scope.description = "description of test qual name";
    //TODO(N): Saved is being tracked internally, should externallize to track array state using external entity
    $scope.lines = [
        //{"description": "des1", "id": 1114112, "mandatory": false, "months": 3, "ordinal": 1, "saved": true},
        //{"description": "", "id": 1114133, "mandatory": false, "months": 0, "ordinal": 4, "saved": true},
        //{"description": "des true", "id": 1114137, "mandatory": false, "months": 0, "ordinal": 3, "saved": true},
        //{"description": "des true 2", "id": 1146881, "mandatory": true, "months": 666, "ordinal": 8, "saved": true}
    ];
    $scope.lineNumbers = []; //array of numbers same size as $scope.lines, TODO: there is probably a better way to do this (use $scope.lines only)
    $scope.getLineNumbers = function() {
        console.log("in getLinesNumbers");
        console.log("   $scope.lineNumbers.length: " + $scope.lineNumbers.length);
        console.log("   $scope.lines.length: " + $scope.lines.length);
        if ($scope.lineNumbers.length === $scope.lines.length) {
            console.log("   $scope.getLinesNumbers, returning existing array");
        } else {//rebuild line numbers
            console.log("   $scope.getLinesNumbers, computing new array");
            $scope.lineNumbers = [];
            for (i = 0; i < $scope.lines.length; i++) {
                $scope.lineNumbers[i] = i + 1;
            }
        }
        console.log("returning");
        return $scope.lineNumbers;
    };

    $scope.moveLine = function(from_index) {
        //console.log("&&&&&&&moveLine called with: " + from_index + " and the lines ordinal contains: " + $scope.lines[from_index].ordinal);
        var target_index = $scope.lines[from_index].ordinal - 1;
        var temp = $scope.lines[from_index];
        $scope.lines.splice(from_index, 1);//delete old
        $scope.lines.splice(target_index, 0, temp);//insert new
        for (var i = 0; i < $scope.lines.length; i++) {
            $scope.lines[i].ordinal = i + 1;
        }
    };

    $scope.savedLines = [];

    $scope.mandatoryOptions = [true, false];
    $scope.isLineSaved = function(index) {
        if (debug === true) {
            console.log("$scope.lines[index]: " + $scope.lines[index]);
            console.log("$scope.savedLines[index]: " + $scope.savedLines[index]);
            if ($scope.lines[index] && $scope.savedLines[index]) {
                console.log("$scope.lines[index].description === $scope.savedLines[index].description: " + ($scope.lines[index].description === $scope.savedLines[index].description));
                console.log("$scope.lines[index].mandatory === $scope.savedLines[index].mandatory: " + ($scope.lines[index].mandatory === $scope.savedLines[index].mandatory));
                console.log("$scope.lines[index].months == $scope.savedLines[index].months: " + ($scope.lines[index].months == $scope.savedLines[index].months));
            }
            if ($scope.lines[index]) {
                console.log("$scope.lines[index].description: " + $scope.lines[index].description);
                console.log("$scope.lines[index].mandatory: " + $scope.lines[index].mandatory);
                console.log("$scope.lines[index].months: " + $scope.lines[index].months);
            }
            if ($scope.savedLines[index]) {
                console.log("$scope.savedLines[index].description: " + $scope.savedLines[index].description);
                console.log("$scope.savedLines[index].mandatory: " + $scope.savedLines[index].mandatory);
                console.log("$scope.savedLines[index].months: " + $scope.savedLines[index].months);
            }
        }

        if ($scope.lines[index] && $scope.savedLines[index] &&
                $scope.lines[index].description === $scope.savedLines[index].description &&
                $scope.lines[index].mandatory === $scope.savedLines[index].mandatory &&
                $scope.lines[index].months == $scope.savedLines[index].months) { //need fuzy comparison, because change turns number to string and strict compare will not work
            return true;
        } else {
            return false;
        }
    };
    $scope.addLine = function() {
        $scope.lines.push({"description": "", "id": "", "mandatory": false, "months": 0, "ordinal": $scope.lines.length + 1});
    };
    $scope.saveLine = function(index) {
//if line has id
        var line = $scope.lines[index];
        var enbalm = {
            "qualId.id": $scope.qual_id,
            "ordinal": line.ordinal,
            "description": line.description,
            "mandatory": line.mandatory,
            "months": line.months
        };
        if (k_isnum(line.id)) { //id exists, update the line
            var id = line.id;
            var config = {};
            //var postData = $scope.lines[id];
            $http.get($scope.qualLineUrl + "/" + id + "/update", {params: enbalm}).success(function(data, status, headers, config) {
                if (data.status === "success") {
                    $.extend($scope.savedLines[index], $scope.lines[index]); //move this line into saved lines
                } else {
                    alert("Failed to save: " + data.message);
                }
            }).error(function(data, status, headers, config) {
                alert("Failed to save line");
            });
        } else {//no id, create new line
            $http.get($scope.addQualLineUrl, {params: enbalm}).success(function(data, status, headers, config) {
                if (data.status === "success") {
                    $scope.lines[index].id = data.entity.id;
                    $scope.savedLines[index] = {}; //next line won't work because "$scope.savedLines[index]" is undefined, can't copy properties to undefined!
                    $.extend($scope.savedLines[index], $scope.lines[index]); //move this line into saved lines
                } else {
                    alert("Failed to save: " + data.message);
                }
            }).error(function(data, status, headers, config) {
                alert("There was an error");
            });
        }
    };
    $scope.deleteLine = function(index) {
        if (k_isnum($scope.lines[index].id)) {
            $http.get($scope.delQualLineUrl, {params: {"id": $scope.lines[index].id}}).success(function(data, status, headers, config) {
//TODO: need to check data for error object
                $scope.savedLines.splice(index, 1);
                $scope.lines.splice(index, 1);
            }).error(function(data, status, headers, config) {
                alert("There was an error");
            });
        } else {
            $scope.lines.splice(index, 1);
        }
    };
    $scope.loadHeader = function() {
        $http.get($scope.readEntityUrl).success(function(data, status, headers, config) {
            $scope.name = data.name;
            $scope.role = data.role;
            $scope.description = data.description;
        }).error(function(data, status, headers, config) {
            alert("There was an error");
        });
    };
    $scope.loadLines = function() {
        $http.get($scope.getLinesUrl).success(function(data, status, headers, config) {
            $scope.lines = data;
            $scope.savedLines = [];
            $.extend(true, $scope.savedLines, $scope.lines); //perform deep copy of lines to savedLines
        }).error(function(data, status, headers, config) {
            alert("There was an error");
        });
    };
    $scope.loadHeader();
    $scope.loadLines();
}

