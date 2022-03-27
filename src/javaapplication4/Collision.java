package javaapplication4;
public class Collision {
    public int absmin(int a,int b){
        return Math.abs(a)<Math.abs(b)?a:b;
    }
    public double absmin(double a,double b){
        return Math.abs(a)<Math.abs(b)?a:b;
    }
    public int absmax(int a,int b){
        return Math.abs(a)>Math.abs(b)?a:b;
    }
    public double absmax(double a,double b){
        return Math.abs(a)>Math.abs(b)?a:b;
    }
    public Tuple2<Boolean,int[]> collide_box_box(int x1,int y1,int w1,int h1,int x2,int y2,int w2,int h2){//碰撞-长方形与长方形
        Tuple2<Boolean,int[]> result=new Tuple2<>(x1<=w2+x2&&w1+x1>=x2&&y1<=h2+y2&&h1+y1>=y2,null);
        if(result.o1){
            int movex=absmin(x1-x2-w2,x1+w1-x2);
            int movey=absmin(y1-y2-h2,y1+h1-y2);
            result.o2=Math.abs(movex)<Math.abs(movey)?new int[]{movex,0}:new int[]{0,movey};
        }
        return result;
    }
    public Tuple2<Boolean,int[]> collide_box_point(int x1,int y1,int w1,int h1,int x2,int y2){//碰撞-长方形与点
        Tuple2<Boolean,int[]> result=new Tuple2<>(x2<=w1+x1 && x2>=x1 && y2<=h1+y1 && y2>=y1,null);
        if(result.o1){
            int movex=absmin(x1-x2,x1+w1-x2);
            int movey=absmin(y1-y2,y1+h1-y2);
            result.o2=Math.abs(movex)<Math.abs(movey)?new int[]{movex,0}:new int[]{0,movey};
        }
        return result;
    }
    public Tuple2<Boolean,int[]> collide_box_circle(int x1,int y1,int w1,int h1,int x2,int y2,int r2){//碰撞-长方形与圆
        return x2<x1 && y2<y1?collide_circle_point(x1,y1,r2,x2,y2):(x2<x1 && y2>y1+h1?collide_circle_point(x1,y1+h1,r2,x2,y2):(x2>x1+w1 && y2<y1?collide_circle_point(x1+w1,y1,r2,x2,y2):(x2>x1+w1 && y2>y1+h1?collide_circle_point(x1+w1,y1+h1,r2,x2,y2):collide_box_point(x1-r2,y1-r2,w1+r2+r2,h1+r2+r2,x2,y2))));
    }
    public Tuple2<Boolean,int[]> collide_circle_point(int x1,int y1,int r1,int x2,int y2){//碰撞-圆与点
        double len=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        Tuple2<Boolean,int[]> result=new Tuple2<>(r1>len,null);
        if(result.o1){
            double similarity_ratio=(r1-len)/len;
            result.o2=new int[]{(int)(similarity_ratio*(x2-x1)),(int)(similarity_ratio*(y2-y1))};
        }
        return result;
    }
    public Tuple2<Boolean,int[]> collide_circle_circle(int x1,int y1,int r1,int x2,int y2,int r2){//碰撞-圆与圆
        double len=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
        Tuple2<Boolean,int[]> result=new Tuple2<>(r1+r2>len,null);
        if(result.o1){
            double similarity_ratio=(r1+r2-len)/len;
            result.o2=new int[]{(int)(similarity_ratio*(x2-x1)),(int)(similarity_ratio*(y2-y1))};
        }
        return result;
    }
    public Tuple2<Boolean,int[]> collide_polygon_polygon(int[][] points1,int[][] points2){//碰撞-突多边形与突多边形
        Tuple3<Boolean,int[],Integer>[] data=new Tuple3[points1.length+points2.length];
        int minlen=Integer.MAX_VALUE;
        int minindex=0;
        for(int i=0;i<data.length;i++){
            if(i<points1.length){
                data[i]=projection_polygon_polygon(i,i==points1.length-1?0:i+1,points1,points2);
            }else{
                data[i]=projection_polygon_polygon(i-points1.length,i==data.length-1?0:i-points1.length+1,points2,points1);
                if(data[i].o1)data[i].o2=new int[]{-data[i].o2[0],-data[i].o2[1]};
            }
            if(!data[i].o1)return data[i];
            if(minlen>data[i].o3){
                minlen=data[i].o3;
                minindex=i;
            }
        }
        return new Tuple2<>(true,data[minindex].o2);
    }
    public Tuple3<Boolean,int[],Integer> projection_polygon_polygon(int index1,int index2,int[][] points1,int[][] points2){//单次投影计算-突多边形与突多边形
        int x1=points1[index1][0];
        int y1=points1[index1][1];
        int x2=points1[index2][0];
        int y2=points1[index2][1];
        Tuple3<Boolean,int[],Integer> result=new Tuple3<>(null,null,null);
        int mpx=(int)((x1+x2)*0.5);
        int mpy=(int)((y1+y2)*0.5);
        int minx1=mpx;
        int maxx1=mpx;
        int miny1=mpy;
        int maxy1=mpy;
        int minx2=Integer.MAX_VALUE;
        int maxx2=Integer.MIN_VALUE;
        int miny2=Integer.MAX_VALUE;
        int maxy2=Integer.MIN_VALUE;
        int i;
        if(x1==x2){
            for(i=0;i<points1.length;i++){
                if(minx1>points1[i][0])minx1=points1[i][0];
                if(maxx1<points1[i][0])maxx1=points1[i][0];
            }
            for(i=0;i<points2.length;i++){
                if(minx2>points2[i][0])minx2=points2[i][0];
                if(maxx2<points2[i][0])maxx2=points2[i][0];
            }
            result.o1=minx1<=maxx2 && maxx1>=minx2;
            if(result.o1){
                result.o2=new int[]{minx1==mpx?minx1-maxx2:maxx1-minx2,0};
                result.o3=result.o2[0]*result.o2[0];
            }
        }else if(y1==y2){
            for(i=0;i<points1.length;i++){
                if(miny1>points1[i][1])miny1=points1[i][1];
                if(maxy1<points1[i][1])maxy1=points1[i][1];
            }
            for(i=0;i<points2.length;i++){
                if(miny2>points2[i][1])miny2=points2[i][1];
                if(maxy2<points2[i][1])maxy2=points2[i][1];
            }
            result.o1=miny1<=maxy2 && maxy1>=miny2;
            if(result.o1){
                result.o2=new int[]{0,miny1==mpy?miny1-maxy2:maxy1-miny2};
                result.o3=result.o2[1]*result.o2[1];
            }
        }else{
            double a1=(y1-y2)/(x1-x2);
            double a2=1/a1;
            double part1=mpy+a2*mpx;
            double part2=a1*part1;
            double part3=a1+a2;
            int tempPointx;
            int tempPointy;
            for(i=0;i<points1.length;i++){
                if(i==index1 || i==index2)continue;
                tempPointx=(int)((points1[i][0]*a1-points1[i][1]+part1)/part3);
                tempPointy=(int)((part2-a2*(points1[i][0]*a1-points1[i][1]))/part3);
                if(minx1>tempPointx){
                    minx1=tempPointx;
                    miny1=tempPointy;
                }
                if(maxx1<tempPointx){
                    maxx1=tempPointx;
                    maxy1=tempPointy;
                }
            }
            for(i=0;i<points2.length;i++){
                tempPointx=(int)((points2[i][0]*a1-points2[i][1]+part1)/part3);
                tempPointy=(int)((part2-a2*(points2[i][0]*a1-points2[i][1]))/part3);
                if(minx2>tempPointx){
                    minx2=tempPointx;
                    miny2=tempPointy;
                }
                if(maxx2<tempPointx){
                    maxx2=tempPointx;
                    maxy2=tempPointy;
                }
            }
            result.o1=minx1<=maxx2 && maxx1>=minx2;
            if(result.o1){
                result.o2=minx1==mpx?new int[]{minx1-maxx2,miny1-maxy2}:new int[]{maxx1-minx2,maxy1-miny2};
                result.o3=result.o2[0]*result.o2[0]+result.o2[1]*result.o2[1];
            }
        }
        return result;
    }
    public Tuple2<Boolean,int[]> collide_polygon_circle(int[][] points,int cx,int cy,int r){//碰撞-突多边形与圆
        Tuple3<Boolean,int[],Integer>[] data=new Tuple3[points.length];
        int templen;
        int minlen1=Integer.MAX_VALUE;
        int minindex1=0;
        int maxlen1=Integer.MIN_VALUE;
        int maxindex1=0;
        int minlen2=Integer.MAX_VALUE;
        int minindex2=0;
        int i;
        for(i=0;i<points.length;i++){
            templen=(points[i][0]-cx)*(points[i][0]-cx)+(points[i][1]-cy)*(points[i][1]-cy);
            if(templen<minlen1){
                minlen1=templen;
                minindex1=i;
            }
            if(maxlen1<templen){
                maxlen1=templen;
                maxindex1=i;
            }
            data[i]=projection_polygon_circle(i,i==points.length-1?0:i+1,points,cx,cy,r);
            if(!data[i].o1)return new Tuple2<>(false,null);
            if(minlen2>data[i].o3){
                minlen2=data[i].o3;
                minindex2=i;
            }
            if(minlen2>data[i].o3){
                minlen2=data[i].o3;
                minindex2=i;
            }
        }
        if(cx==points[minindex1][0]){
            if(!((cy<points[minindex1][1]&&cy>points[maxindex1][1])||(cy>points[minindex1][1]&&cy<points[maxindex1][1])))return new Tuple2<>(false,null);
            minlen1=points[minindex1][1]+((cx<points[minindex1][0]&&cx>points[maxindex1][0])||(cx>points[minindex1][0]&&cx<points[maxindex1][0])?-r:r)-cy;
            if(minlen1*minlen1<minlen2)return new Tuple2<>(false,new int[]{0,minlen1});
        }else if(cy==points[minindex1][1]){
            if(!((cx+r<points[minindex1][0]&&cx>points[maxindex1][0]+r)||(cx>points[minindex1][0]+r&&cx+r<points[maxindex1][0])))return new Tuple2<>(false,null);
            minlen1=points[minindex1][0]+((cx<points[minindex1][0]&&cx>points[maxindex1][0])||(cx>points[minindex1][0]&&cx<points[maxindex1][0])?-r:r)-cx;
            if(minlen1*minlen1<minlen2)return new Tuple2<>(false,new int[]{minlen1,0});
        }else{
            double a1=(cy-points[minindex1][1])/(cx-points[minindex1][0]);
            double part1=points[minindex1][1]+a1*points[minindex1][0];
            double part2=Math.sqrt(a1*a1-1);
            double csx=(part2*cx+(points[maxindex1][0]<points[minindex1][0]?-r:r))/part2;
            double furthestProjectionX=(points[maxindex1][0]*a1-points[maxindex1][1]+part1)/(a1+1/a1);
            if((csx<points[minindex1][0]&&csx>furthestProjectionX)||(csx>points[minindex1][0]&&csx<furthestProjectionX)){
                double movey=points[minindex1][1]-csx*a1+a1*cx-cy;
                if(movey*movey+(points[minindex1][0]-csx)*(points[minindex1][0]-csx)<minlen2)return new Tuple2<>(true,new int[]{points[minindex1][0]-(int)csx,(int)movey});
            }else return new Tuple2<>(false,null);
        }
        return new Tuple2<>(true,data[minlen2>minlen1?minindex1:minindex2].o2);
    }
    public Tuple3<Boolean,int[],Integer> projection_polygon_circle(int index1,int index2,int[][] points,int cx,int cy,int r){//单次投影计算-突多边形与圆
        int x1=points[index1][0];
        int y1=points[index1][1];
        int x2=points[index2][0];
        int y2=points[index2][1];
        Tuple3<Boolean,int[],Integer> result=new Tuple3<>(null,null,null);
        int mpx=(int)((x1+x2)*0.5);
        int mpy=(int)((y1+y2)*0.5);
        int minx1=mpx;
        int maxx1=mpx;
        int miny1=mpy;
        int maxy1=mpy;
        int minx2=Integer.MAX_VALUE;
        int maxx2;
        int miny2;
        int maxy2;
        int i;
        if(x1==x2){
            for(i=0;i<points.length;i++){
                if(minx1>points[i][0])minx1=points[i][0];
                if(maxx1<points[i][0])maxx1=points[i][0];
            }
            minx2=cx-r;
            maxx2=cx+r;
            result.o1=minx1<=maxx2 && maxx1>=minx2;
            if(result.o1){
                result.o2=new int[]{minx1==mpx?minx1-maxx2:maxx1-minx2,0};
                result.o3=result.o2[0]*result.o2[0];
            }
        }else if(y1==y2){
            for(i=0;i<points.length;i++){
                if(miny1>points[i][1])miny1=points[i][1];
                if(maxy1<points[i][1])maxy1=points[i][1];
            }
            miny2=cy-r;
            maxy2=cy+r;
            result.o1=miny1<=maxy2 && maxy1>=miny2;
            if(result.o1){
                result.o2=new int[]{0,miny1==mpy?miny1-maxy2:maxy1-miny2};
                result.o3=result.o2[1]*result.o2[1];
            }
        }else{
            double a1=(y1-y2)/(x1-x2);
            double a2=1/a1;
            double part1=mpy+a2*mpx;
            double part2=a1*part1;
            double part3=a1+a2;
            double b=a2*cx+cy;
            double a3=1+a2*a2;
            double b2=2*cx+2*a2*(b-cy);
            double part4=Math.sqrt(b2*b2-4*a3*(cx*cx+(b-cy)*(b-cy)-r*r));
            int tempPointx,tempPointy;
            double tempy=-a2*minx2+b;
            minx2=(int)((b2-part4)/(2*a3));
            minx2=(int)((minx2*a1-tempy+part1)/part3);
            miny2=(int)((part2-a2*(minx2*a1-tempy))/part3);
            maxx2=(int)((b2+part4)/(2*a3));
            maxx2=(int)((maxx2*a1-tempy+part1)/part3);
            maxy2=(int)((part2-a2*(maxx2*a1-tempy))/part3);
            for(i=0;i<points.length;i++){
                if(i==index1 || i==index2)continue;
                tempPointx=(int)((points[i][0]*a1-points[i][1]+part1)/part3);
                tempPointy=(int)((part2-a2*(points[i][0]*a1-points[i][1]))/part3);
                if(minx1>tempPointx){
                    minx1=tempPointx;
                    miny1=tempPointy;
                }
                if(maxx1<tempPointx){
                    maxx1=tempPointx;
                    maxy1=tempPointy;
                }
            }
            result.o1=minx1<=maxx2 && maxx1>=minx2;
            if(result.o1){
                result.o2=minx1==mpx?new int[]{minx1-maxx2,miny1-maxy2}:new int[]{maxx1-minx2,maxy1-miny2};
                result.o3=result.o2[0]*result.o2[0]+result.o2[1]*result.o2[1];
            }
        }
        return result;
    }
}
