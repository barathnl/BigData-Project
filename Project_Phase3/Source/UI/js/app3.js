	google.charts.load('upcoming', {'packages': ['geochart']});
    google.charts.setOnLoadCallback(drawMarkersMap);

	var allText;
	readTextFile();
	var out='{"loc":'+allText+'}';
	var json = JSON.parse(out);
	
	var locname1 = json.loc[0].Location;
	var locname2 = json.loc[1].Location;
	var locname3 = json.loc[2].Location;
	var locname4 = json.loc[3].Location;
	var locname5 = json.loc[4].Location;
	
	var loc1 = json.loc[0].LoctionCount;
	var loc2 = json.loc[1].LoctionCount;
	var loc3 = json.loc[2].LoctionCount;
	var loc4 = json.loc[3].LoctionCount;
	var loc5 = json.loc[4].LoctionCount;
	
	//Function to point graph
      function drawMarkersMap() {
     
	    var data = new google.visualization.DataTable();
			data.addColumn('string', 'Location');
			data.addColumn('number', 'count');
			data.addRows(5);
			data.setValue(0, 0, locname1);
			data.setValue(0, 1, parseInt(loc1));
			data.setValue(1, 0, locname2);
			data.setValue(1, 1, parseInt(loc2));
			data.setValue(2, 0, locname3);
			data.setValue(2, 1, parseInt(loc3));
			data.setValue(3, 0, locname4);
			data.setValue(3, 1, parseInt(loc4));
			data.setValue(4, 0, locname5);
			data.setValue(4, 1, parseInt(loc5));
			

      var options = {
	   title: 'Location_Counts',
        displayMode: 'markers',
        colorAxis: {colors: ['green', 'blue']}
      };

      var chart = new google.visualization.GeoChart(document.getElementById('chart_div1'));
      chart.draw(data, options);
    };
	
	function readTextFile()
		{
			var rawFile = new XMLHttpRequest();
			rawFile.open("GET","file:///C:/Users/barat/Desktop/PB_Project/phase3/input/intermediateOutput3.txt", false);
			rawFile.onreadystatechange = function ()
			{
				if(rawFile.readyState === 4)
				{
					if(rawFile.status === 200 || rawFile.status == 0)
					{
						allText = rawFile.responseText;
						//console.log(allText)
					}
				}
			}
			rawFile.send(null);
		}