	google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

	var allText;
	readTextFile();
	var out='{"tweet":'+allText+'}';
	var json = JSON.parse(out);
	var retweet1 = json.tweet[0].Retweet_Count;
	var retweet2 = json.tweet[1].Retweet_Count;
	var retweet3 = json.tweet[2].Retweet_Count;
	var retweet4 = json.tweet[3].Retweet_Count;
	
	var retweetText1 = json.tweet[0].Retweet_Text;
	var retweetText2 = json.tweet[1].Retweet_Text;
	var retweetText3 = json.tweet[2].Retweet_Text;
	var retweetText4 = json.tweet[3].Retweet_Text;
	
	//Function to plot Area Chart	
    function drawChart() {
        var data = new google.visualization.DataTable();
			data.addColumn('string', 'Text');
			data.addColumn('number', 'count');
			data.addRows(5);
			data.setValue(0, 0, 'Text1');
			data.setValue(0, 1, parseInt(retweet1));
			data.setValue(1, 0, 'Text2');
			data.setValue(1, 1, parseInt(retweet2));9
			data.setValue(2, 0, 'Text3');
			data.setValue(2, 1, parseInt(retweet3));
			data.setValue(3, 0, 'Text4');
			data.setValue(3, 1, parseInt(retweet4));
				
        var options = {
          title: 'Retweet_Count',
          hAxis: {title: 'Sr.No',  titleTextStyle: {color: '#333'}},
          vAxis: {minValue: 0}
        };

        var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
	  
	  //reading input file of query2 intermediateOutput2
	  function readTextFile()
		{
			var rawFile = new XMLHttpRequest();
			rawFile.open("GET","file:///C:/Users/barat/Desktop/PB_Project/phase3/input/intermediateOutput2.txt", false);
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