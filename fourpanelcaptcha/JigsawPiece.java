

import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.Random;

class JigsawPiece extends Canvas
{

    JigsawPiece(Image image, int i, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int i2, int j2, int k2, int l2, int i3, 
            int ai[], int j3)
    {
        rNum = new Random();
        basePosX = j + (jPosX = j1);
        basePosY = k + (jPosY = k1);
        xPos = l1;
        yPos = i2;
        fc = new int[4];
        fc[0] = j2;
        fc[1] = k2;
        fc[2] = l2;
        fc[3] = i3;
        orient = j3;
        if(j2 > 0)
        {
            if(k2 != 0)
                if(k2 > 0)
                    k2 += i - 3;
                else
                    k2 -= i - 3;
            if(i3 != 0)
                if(i3 > 0)
                    i3 += i - 3;
                else
                    i3 -= i - 3;
        }
        if(i3 > 0)
        {
            if(j2 != 0)
                if(j2 > 0)
                    j2 += i - 3;
                else
                    j2 -= i - 3;
            if(l2 != 0)
                if(l2 > 0)
                    l2 += i - 3;
                else
                    l2 -= i - 3;
        }
        JigsawCutter jigsawcutter = new JigsawCutter(0, false, sizeW = l, sizeH = i1, overlap = i, j2, k2, l2, i3, ai);
        Image image1 = createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(j, k, l, i1)));
        img = createImage(new FilteredImageSource(image1.getSource(), jigsawcutter));
        setVisible(false);
    }

    public int getBaseX()
    {
        return basePosX;
    }

    public int getBaseY()
    {
        return basePosY;
    }

    public int getFace(int i)
    {
        return fc[i];
    }

    public int getH()
    {
        return sizeH;
    }

    public int getOrientation()
    {
        return orient;
    }

    public Image getPiece()
    {
        return img;
    }

    public int getW()
    {
        return sizeW;
    }

    public int getX()
    {
        return xPos;
    }

    public int getY()
    {
        return yPos;
    }

    public boolean isOver(int i, int j)
    {
        return i >= xPos && i <= xPos + sizeW && j >= yPos && j <= yPos + sizeH;
    }

    public void setOrientation(int i)
    {
        if(i == 0)
        {
            orient = 0;
        } else
        {
            orient++;
            if(orient > 3)
                orient = 0;
        }
    }

    public void setPosition(int i, int j)
    {
        xPos = i;
        yPos = j;
    }

    Image img;
    int xPos;
    int yPos;
    int basePosX;
    int basePosY;
    int jPosX;
    int jPosY;
    int sizeW;
    int sizeH;
    int overlap;
    int orient;
    Random rNum;
    int fc[];
}