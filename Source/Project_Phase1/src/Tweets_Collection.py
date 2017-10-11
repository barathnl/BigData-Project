#Import the necessary methods from tweepy library
from tweepy.streaming import StreamListener
from tweepy import OAuthHandler
from tweepy import Stream

#Variables that contains the user credentials to access Twitter API 
access_token = "2935099867-8tNtrkyR0Rggg5AzV5ebmibmsnZKQxRw90nrDfa"
access_token_secret = "bduYTPwQwXQi5suSLI9AMmwAxFAj0u7M4N4uX5TgXy9FF"
consumer_key = "OmCUg40OHHyqvTUb24hx3F1mQ"
consumer_secret = "BMnGdkIJAlWMyp18ff9rzcFZlkdWJjUUcQc3WExyHvc27rnhcQ"


#This is a basic listener that just prints received tweets to stdout.
class StdOutListener(StreamListener):

    def on_data(self, data):
        print(data)
        import json
        with open('tweet_data.txt','a') as outfile:
            json.dump(data,outfile)
        return True

    def on_error(self, status):
        print(status)


if __name__ == '__main__':

    #This handles Twitter authetification and the connection to Twitter Streaming API
    l = StdOutListener()
    auth = OAuthHandler(consumer_key, consumer_secret)
    auth.set_access_token(access_token, access_token_secret)
    stream = Stream(auth, l)

    #This line filter Twitter Streams to capture data by the keywords: 'python', 'javascript', 'ruby'
    stream.filter(track=['python', 'javascript', 'ruby'])
