  
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);
	
	var allText;
	readTextFile();
	var out='{"lang":'+allText+'}';
	var json = JSON.parse(out);
	var num1 = json.lang[0].Language_Count;
	var num2 = json.lang[1].Language_Count;
	
	//Function to plot Pie Chart
	function drawChart() {
		
        
		var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Task');
                    data.addColumn('number', 'Hours per Day');
                    data.addRows(5);
                    data.setValue(0, 0, 'English');
                    data.setValue(0, 1, parseInt(num1));
                    data.setValue(1, 0, 'Hindi');
                    data.setValue(1, 1, parseInt(num2));
                    

        var options = {
          title: 'Language Count',
          is3D: true,
		   
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
	  
	  //reading input file of query1 intermediateOutput1
	  function readTextFile()
		{
			var rawFile = new XMLHttpRequest();
			rawFile.open("GET","file:///C:/Users/barat/Desktop/PB_Project/phase3/input/intermediateOutput1.txt", false);
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
		