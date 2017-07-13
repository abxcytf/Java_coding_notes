/*
535. Encode and Decode TinyURL

Note: This is a companion problem to the System Design problem: Design TinyURL.
TinyURL is a URL shortening service where you enter a URL such 
as https://leetcode.com/problems/design-tinyurl and it returns a 
short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. 
There is no restriction on how your encode/decode algorithm should work. 
You just need to ensure that a URL can be encoded to a tiny URL and the 
tiny URL can be decoded to the original URL.
*/

public class Codec {
    //Approach 1- Using simple counter - using hashMap index to compose the shortUrl
    Map<Integer, String> map = new HashMap<>();
    int index = 0;
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        map.put(index, longUrl);
        return "http://tinyurl.com/" + index++;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
    }
    
    
    /********************************************************************************************/
    
    
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
