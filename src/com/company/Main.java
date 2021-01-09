package com.company;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        double[] re = Test.getResolutions(18);
        double[] extent = {106.54,21.59,108.26,23.20};

        for(int z=0;z<18;z++){
            int totalX = (int)Math.ceil(360.0/(re[z]*256.0)); //列数向上取整
            int totalY = (int)Math.ceil(180.0/(re[z]*256.0)); //行数向上取整 */

            //起始结束列
            int sX = (int)Math.floor(((extent[0] + 180) / 360) * totalX);
            int eX = (int)Math.floor(((extent[2] + 180) / 360) * totalX);

            //起始结束行
            int sY = (int)Math.floor(((90 - extent[3]) / 180) * totalY);
            int eY = (int)Math.floor(((90 - extent[1]) / 180) * totalY);

            for(int y=sY;y<=eY;y++){
                for(int x=sX;x<=eX;x++){
                    //http://t0.tianditu.com/vec_w/wmts?service=wmts&request=gettile&version=1.0.0&layer=vec&format=tiles&tilematrixset=w&tk=502e05b68ea427c201f63ec608d7f0d7
                    String urlstr = "http://t0.tianditu.com/DataServer?T=vec_c&x="+x+"&y="+y+"&l="+z+"&tk=502e05b68ea427c201f63ec608d7f0d7";
                    //天地图服务器t0-t8间选一个
                    System.out.println(urlstr);
                    String path = "/Users/admin/Desktop/maptiles/"+z + File.separator+y+File.separator+x+".png";
                    File file = new File(path);
                    URL url = null;
                    try {
                        url = new URL(urlstr);
                        Test.download(url,file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
