

import java.awt.image.RGBImageFilter;

class JigsawCutter extends RGBImageFilter
{

    public JigsawCutter(int i, boolean flag, int j, int k, int l, int i1, int j1, 
            int k1, int l1, int ai[])
    {
        dimValue = i;
        grayScale = flag;
        overlap = l;
        f0 = i1;
        f1 = j1;
        f2 = k1;
        f3 = l1;
        w = j;
        h = k;
        points = ai;
    }

    private int darkenRGB(int i, int j, int k, int l)
    {
        if((i = (i * (100 - l)) / 100 - 10) < 0)
            i = 0;
        if((j = (j * (100 - l)) / 100 - 10) < 0)
            j = 0;
        if((k = (k * (100 - l)) / 100 - 10) < 0)
            k = 0;
        return 0xff000000 | i << 16 | j << 8 | k;
    }

    public int filterRGB(int i, int j, int k)
    {
        int l = k >> 16 & 0xff;
        int i1 = k >> 8 & 0xff;
        int j1 = k & 0xff;
        int k1 = overlap - 3;
        if(dimValue > 0)
            if(grayScale)
            {
                int l1 = (l + i1 + j1) / 3;
                return lightenRGB(l1, l1, l1, dimValue);
            } else
            {
                return lightenRGB(l, i1, j1, dimValue);
            }
        int i2 = overlap / 2;
        int j2 = f0 < 0 ? -f0 : f0;
        int k2 = f1 < 0 ? -f1 : f1;
        int l2 = f2 < 0 ? -f2 : f2;
        int i3 = f3 < 0 ? -f3 : f3;
        if(i < k1)
        {
            if(j < k1 && f0 > 0 || j >= h - k1 && f2 > 0 || (j < i3 - i2 || j > i3 + i2) && f3 > 0)
                return 0;
            if(j >= i3 - i2 && j <= i3 + i2 && f3 < 0)
                return holeCutter(3, i3, i, j - (i3 - i2), l, i1, j1);
            if(j >= i3 - i2 && j <= i3 + i2 && f3 > 0)
                return stubCutter(3, i3, i, j - (i3 - i2), l, i1, j1);
        }
        if(i >= w - k1)
        {
            if(j < k1 && f0 > 0 || j >= h - k1 && f2 > 0 || (j < k2 - i2 || j > k2 + i2) && f1 > 0)
                return 0;
            if(j >= k2 - i2 && j <= k2 + i2 && f1 < 0)
                return holeCutter(1, k2, i - (w - overlap), j - (k2 - i2), l, i1, j1);
            if(j >= k2 - i2 && j <= k2 + i2 && f1 > 0)
                return stubCutter(1, k2, i - (w - overlap), j - (k2 - i2), l, i1, j1);
        }
        if(j < k1)
        {
            if(i < k1 && f3 > 0 || i >= w - k1 && f1 > 0 || (i < j2 - i2 || i > j2 + i2) && f0 > 0)
                return 0;
            if(i >= j2 - i2 && i <= j2 + i2 && f0 < 0)
                return holeCutter(0, j2, i - (j2 - i2), j, l, i1, j1);
            if(i >= j2 - i2 && i <= j2 + i2 && f0 > 0)
                return stubCutter(0, j2, i - (j2 - i2), j, l, i1, j1);
        }
        if(j >= h - k1)
        {
            if(i < k1 && f3 > 0 || i >= w - k1 && f1 > 0 || (i < l2 - i2 || i > l2 + i2) && f2 > 0)
                return 0;
            if(i >= l2 - i2 && i <= l2 + i2 && f2 < 0)
                return holeCutter(2, l2, i - (l2 - i2), j - (h - overlap), l, i1, j1);
            if(i >= l2 - i2 && i <= l2 + i2 && f2 > 0)
                return stubCutter(2, l2, i - (l2 - i2), j - (h - overlap), l, i1, j1);
        }
        if(j == 0 && f0 < 0 || j == overlap - 3 && f0 > 0 && (i < j2 - 2 || i > j2 + 2))
            return darkenRGB(l, i1, j1, 15);
        if(j == 1 && f0 <= 0 || j == overlap - 2 && f0 > 0 && (i < j2 - 2 || i > j2 + 2))
            return lightenRGB(l, i1, j1, 20);
        if(j == 2 && f0 <= 0 || j == overlap - 1 && f0 > 0 && (i < j2 - 2 || i > j2 + 2))
            return lightenRGB(l, i1, j1, 15);
        if(i == w - 1 && f1 <= 0 || w - 1 - i == overlap - 3 && f1 > 0 && (j < k2 - 2 || j > k2 + 2))
            return lightenRGB(l, i1, j1, 20);
        if(j == h - 1 && f2 <= 0 || h - 1 - j == overlap - 3 && f2 > 0 && (i < l2 - 2 || i > l2 + 2))
            return lightenRGB(l, i1, j1, 20);
        if(i == 0 && f3 < 0 || i == overlap - 3 && f3 > 0 && (j < i3 - 2 || j > i3 + 2))
            return darkenRGB(l, i1, j1, 15);
        if(i == 1 && f3 <= 0 || i == overlap - 2 && f3 > 0 && (j < i3 - 2 || j > i3 + 2))
            return lightenRGB(l, i1, j1, 20);
        if(i == 2 && f3 <= 0 || i == overlap - 1 && f3 > 0 && (j < i3 - 2 || j > i3 + 2))
            return lightenRGB(l, i1, j1, 15);
        else
            return k;
    }

    private int holeCutter(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        int l1 = overlap / 2;
        int i2 = 0;
        int j2 = 0;
        int k2 = 0;
        byte byte0 = 0;
        k++;
        switch(i)
        {
        case 0: // '\0'
            i2 = l;
            j2 = k - 1;
            k2 = 0;
            byte0 = 3;
            break;

        case 1: // '\001'
            j2 = l - 1;
            i2 = k - 3;
            k2 = 0;
            byte0 = 2;
            break;

        case 2: // '\002'
            i2 = l - 2;
            j2 = k - 1;
            k2 = 0;
            byte0 = 2;
            break;

        case 3: // '\003'
            j2 = l - 1;
            i2 = k - 1;
            k2 = 0;
            byte0 = 3;
            break;
        }
        if(i2 >= k2 && i2 + byte0 < 22)
        {
            if(j2 > l1 - points[i2 + byte0] && j2 < l1 + points[i2 + byte0])
                return 0;
            if(j2 >= l1 - points[i2 + byte0] && j2 <= l1 + points[i2 + byte0])
                return darkenRGB(i1, j1, k1, 30);
        }
        return 0xff000000 | i1 << 16 | j1 << 8 | k1;
    }

    private int lightenRGB(int i, int j, int k, int l)
    {
        if((i = 10 + (255 - ((255 - i) * (100 - l)) / 100)) > 255)
            i = 255;
        if((j = 10 + (255 - ((255 - j) * (100 - l)) / 100)) > 255)
            j = 255;
        if((k = 10 + (255 - ((255 - k) * (100 - l)) / 100)) > 255)
            k = 255;
        return 0xff000000 | i << 16 | j << 8 | k;
    }

    private int stubCutter(int i, int j, int k, int l, int i1, int j1, int k1)
    {
        int l1 = overlap / 2;
        int i2 = 0;
        int j2 = 0;
        int k2 = 0;
        byte byte0 = 0;
        k++;
        switch(i)
        {
        case 0: // '\0'
            i2 = l;
            j2 = k - 1;
            k2 = 0;
            byte0 = 3;
            break;

        case 1: // '\001'
            j2 = l - 1;
            i2 = k - 3;
            k2 = 0;
            byte0 = 2;
            break;

        case 2: // '\002'
            i2 = l - 2;
            j2 = k - 1;
            k2 = 0;
            byte0 = 2;
            break;

        case 3: // '\003'
            j2 = l - 1;
            i2 = k - 1;
            k2 = 0;
            byte0 = 3;
            break;
        }
        if(i2 >= k2 && i2 + byte0 < 23)
        {
            if(j2 > l1 - points[i2 + byte0] && j2 < l1 + points[i2 + byte0])
                return 0xff000000 | i1 << 16 | j1 << 8 | k1;
            if(j2 >= l1 - points[i2 + byte0] && j2 <= l1 + points[i2 + byte0])
                return lightenRGB(i1, j1, k1, 30);
        }
        return 0;
    }

    int dimValue;
    int w;
    int h;
    int overlap;
    int f0;
    int f1;
    int f2;
    int f3;
    int points[];
    boolean grayScale;
}