import oauth2 as oauth
import urllib.request
import sys

consumer_key = '7d5h5357s8js'
consumer_secret = 'rmjewpw52mmjzamf'
consumer = oauth.Consumer(consumer_key, consumer_secret)
fileid = sys.argv[1]
request_url = "http://previews.7digital.com/clip/" + fileid
#376386 data\A\J\J\TRAJJIG128F1474EF0.h5

req = oauth.Request(method="GET", url=request_url,is_form_encoded=True)

req['oauth_timestamp'] = oauth.Request.make_timestamp()
req['oauth_nonce'] = oauth.Request.make_nonce()
req['country'] = "CA"
#req['trackd'] = "300848"
sig_method = oauth.SignatureMethod_HMAC_SHA1()

req.sign_request(sig_method, consumer, token=None)

#print (req.to_url() )

f = open('fileurl.txt','w')
f.write(req.to_url())
f.close
