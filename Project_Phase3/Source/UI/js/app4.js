  
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
	
	var allText;
	readTextFile();
	var json = JSON.parse(allText);
	var num1 = json.device1[0].android;
	var num2 = json.device2[0].iphone;
	var num3 = json.device3[0].windows;
	var num4 = json.device4[0].web;
		
	//Function to plot Pie Chart
	function drawChart() {
		
        
		var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Device');
                    data.addColumn('number', 'Device Count');
                    data.addRows(5);
                    data.setValue(0, 0, 'Android');
                    data.setValue(0, 1, parseInt(num1));
                    data.setValue(1, 0, 'iPhone');
                    data.setValue(1, 1, parseInt(num2));
					data.setValue(2, 0, 'Windows');
                    data.setValue(2, 1, parseInt(num3));
					data.setValue(3, 0, 'Web');
                    data.setValue(3, 1, parseInt(num4));
            
		

        var options = {
          title: 'Device',
          is3D: true,
		   
        };

        var chart = new google.visualization.BarChart(document.getElementById('bar_3d'));
        chart.draw(data, options);
      }
	  
	  //reading input file of query1 intermediateOutput1
	  function readTextFile()
		{
			var rawFile = new XMLHttpRequest();
			rawFile.open("GET","file:///C:/Users/barat/Desktop/PB_Project/phase3/input/intermediateOutput4.txt", false);
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
		