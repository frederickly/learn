package hash;

/**
 * https://www.lintcode.com/problem/529/
 * https://www.lintcode.com/problem/530/
 *
 *  529 geohash encode
 *  Description
 *
 * Geohash is a hash function that convert a location coordinate pair into a base32 string.
 *
 * Check how to generate geohash on wiki: Geohash or just google it for more details.
 *
 * Try http://geohash.co/.
 *
 * You task is converting a (latitude, longitude) pair into a geohash string.
 *
 * 1 <= precision <=12
 * Example
 *
 * Example1
 *
 * Input:
 *
 * lat = 39.92816697
 *
 * lng = 116.38954991
 *
 * precision = 12
 *
 * Output: "wx4g0s8q3jf9"
 *
 * Example2
 *
 * Input:
 *
 * lat = -90
 *
 * lng = 180
 *
 * precision = 12
 *
 * Output: "pbpbpbpbpbpb"
 *
 * 530 geohash decode
 * Description
 *
 * This is an enhanced version of Geohash
 * Using the inverse operation of geohash to find latitude and longitude from a string
 * For details of geohash, see helpGeohash or wiki GeoHash.
 * Example
 *
 * Example1
 *
 * Input: "wx4g0s"`
 *
 * Output: lat = 39.92706299 and lng = 116.39465332
 *
 * Example2
 *
 * Input: "w"
 *
 * Output: lat = 22.50000000 and lng = 112.50000000
 */
public class GeoHash {
    private static class Interval{
        enum Type{LONGITUDE, LATITUDE;}
        double lower;
        double higher;

        Interval( Type s){
            switch(s){
                case LONGITUDE :
                    this.lower = -180;
                    this.higher = 180;
                    break;
                case LATITUDE:
                    this.lower = -90;
                    this.higher = 90;
                    break;
            }
        }
        public double avg(){
            return lower + (higher -lower)/2;
        }
        public int narrow(double target){
            double mid = lower + (higher -lower)/2;
            int ans;
            if(target<=mid){
                this.higher = mid;
                ans = 0;
            }else{
                this.lower= mid;
                ans = 1;
            }
            return ans;
        }
        public void narrowByBinary(char s){
            double mid = lower + (higher -lower)/2;
            switch(s){
                case '1':
                    this.lower = mid;
                    break;
                case '0':
                    this.higher = mid;
                    break;
            }
        }
    }

    private static final String BASE32 ="0123456789bcdefghjkmnpqrstuvwxyz";
    private String binary2Base32(String s){
        int counter = 0;
        int sum = 0;
        String ans = "";
        while(counter < s.length()){
            String sub = s.substring(counter,counter+1);
            sum = 2*sum +Integer.parseInt(sub) ;
            counter++;
            if(counter%5 ==0){
                ans+=BASE32.charAt(sum);
                sum=0;
            }
        }
        return ans;
    }
    private String base32binary(String str){
        char[] ch = str.toCharArray();
        String ans="";
        for(char c: ch ){
            int temp = BASE32.indexOf(c);
            String s = "";
            while(temp!=0){
                s = ""+ temp%2 + s;
                temp/=2;
            }
            while(s.length()<5){
                s = "0"+s;
            }
            ans += s;
        }
        return ans;
    }
    public String encode(double latitude, double longitude, int precision) {
        int num = precision*5;
        Interval[] l = new Interval[2];
        l[0] = new Interval(Interval.Type.LONGITUDE);
        l[1] = new Interval(Interval.Type.LATITUDE);
        double[] target = {longitude, latitude};
        String serial = "";
        for(int i= 0; i<num;i++){
            Interval temp = l[i%2];
            serial +=temp.narrow(target[i%2]);
        }
        return binary2Base32(serial);
    }
    public double[] decode(String geohash) {
        String serial = base32binary(geohash);
        Interval[] l = new Interval[2];
        l[Interval.Type.LONGITUDE.ordinal()] = new Interval(Interval.Type.LONGITUDE);
        l[Interval.Type.LATITUDE.ordinal()] = new Interval(Interval.Type.LATITUDE);
        for(int i= 0; i<serial.length();i++){
            Interval temp = l[i%2];
            temp.narrowByBinary(serial.charAt(i));
        }
        double[] ans = new double[2];
        ans[0] =l[Interval.Type.LATITUDE.ordinal()].avg();
        ans[1] =l[Interval.Type.LONGITUDE.ordinal()].avg();
        return ans;

    }


}
