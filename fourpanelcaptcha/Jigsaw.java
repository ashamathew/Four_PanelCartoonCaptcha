

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.FilteredImageSource;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Jigsaw extends Applet
    implements Runnable, ActionListener, MouseListener, MouseMotionListener, WindowListener
{

    public Jigsaw()
    {
        hlpImgFade = -1;
        hlpImgGray = false;
        avoidCenter = false;
        canRotate = false;
        keepClear = false;
        autoSnap = 3;
        cutting = 0;
        ts = 0;
        overlap = 16;
        w = 0;
        h = 0;
        vAlign = 1;
        bgColor = Color.lightGray;
        txColor = Color.black;
        ofColor = Color.black;
        ifColor = Color.black;
        pkColor = Color.red;
        bdColor = new Color(0xffbfbf);
        showSolve = true;
        runner = null;
        bDoneSolve = false;
        isComplete = false;
        bImageLoaded = false;
        tracker = new MediaTracker(this);
        selID = 0;
        bCanReorder = true;
        bAutoShow = true;
        img1 = null;
        tImg = null;
        aImg = null;
        selPP = null;
        rNum = new Random();
        xmOff = 0;
        ymOff = 0;
         STEP_SIZE= 20;
    }

    public void actionPerformed(ActionEvent actionevent)
    {
        String s = actionevent.getActionCommand();
        if(s.equals(t[2]))
        {
            for(int i = 0; i < rows; i++)
            {
                for(int j = 0; j < cols; j++)
                {
                    JigsawPiece jigsawpiece = jp[i * cols + j];
                    jigsawpiece.setPosition(jigsawpiece.getBaseX(), jigsawpiece.getBaseY());
                    jigsawpiece.setOrientation(0);
                }

            }

        }
        if(s.equals(t[0]))
            breakupPuzzle(false);
        if(s.equals(t[1]))
           // breakupPuzzle(true);
        if(s.equals("?"))
            //showAbout();
        repaint();
    }

    private void breakupPuzzle(boolean flag)
    {
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        for(int i1 = 0; i1 < rows; i1++)
        {
            if(bCanReorder)
                reorderList((int)(rNum.nextDouble() * (double)iNumPieces));
            for(int j1 = 0; j1 < cols; j1++)
            {
                boolean flag5 = true;
                JigsawPiece jigsawpiece = jp[i1 * cols + j1];
                if(flag)
                {
                    int k = jigsawpiece.getX();
                    int l = jigsawpiece.getY();
                    if(k >= jPosX && k <= (jPosX + iWidth) - w && l >= jPosY && l <= (jPosY + iHeight) - h)
                        flag5 = false;
                }
                if(flag5)
                {
                    int i;
                    int j;
                    do
                    {
                        if(!flag && canRotate)
                            jigsawpiece.setOrientation((int)(rNum.nextDouble() * 3D));
                        i = (int)(rNum.nextDouble() * (double)(appW - w - 20 - overlap)) + 10;
                        j = (int)(rNum.nextDouble() * (double)(appH - h - 20 - overlap)) + 10;
                    } while((keepClear || flag) && i > jPosX - w - overlap && j >= jPosY - h - overlap && j <= jPosY + iHeight);
                    jigsawpiece.setPosition(i, j);
                }
            }

        }

    }

    public void cutupPicture(Image image)
    {
        int i = 0;
        boolean flag = false;
        int k = 0;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        boolean flag5 = false;
        boolean flag6 = false;
        int ai[] = new int[4];
        int j1 = w / 2;
        int k1 = h / 2;
        boolean flag7 = false;
        boolean flag8 = false;
        overlap = STEP_SIZE - 2;
        for(int i2 = 0; i2 < rows; i2++)
        {
            int j = 0;
            for(int j2 = 0; j2 < cols; j2++)
            {
                int l;
                int i1;
                do
                {
                    l = (int)(rNum.nextDouble() * (double)(appW - w - 10)) + 5;
                    i1 = (int)(rNum.nextDouble() * (double)(appH - h - 10)) + 5;
                } while(l < jPosX + iWidth + 5 && l > jPosX - w - 5 && i1 < jPosY + iHeight + 5 && i1 > jPosY - h - 5 && avoidCenter);
                if(i2 == 0)
                {
                    ai[0] = 0;
                } else
                {
                    JigsawPiece jigsawpiece = jp[(i2 - 1) * cols + j2];
                    ai[0] = -jigsawpiece.getFace(2);
                }
                if(j2 == cols - 1)
                {
                    ai[1] = 0;
                } else
                {
                    ai[1] = h / 2;
                    if(rNum.nextDouble() * 2D - 1.0D < 0.0D)
                        ai[1] = -ai[1];
                    if(ai[0] > 0)
                        if(ai[1] > 0)
                            ai[1] += overlap - 3;
                        else
                            ai[1] -= overlap - 3;
                }
                if(i2 == rows - 1)
                {
                    ai[2] = 0;
                } else
                {
                    ai[2] = w / 2;
                    if(rNum.nextDouble() * 2D - 1.0D < 0.0D)
                        ai[2] = -ai[2];
                }
                if(j2 == 0)
                {
                    ai[3] = 0;
                } else
                {
                    JigsawPiece jigsawpiece1 = jp[i2 * cols + (j2 - 1)];
                    ai[3] = -jigsawpiece1.getFace(1);
                }
                int ai1[] = new int[4];
                for(int k2 = 0; k2 < 4; k2++)
                    ai1[k2] = ai[k2] <= 0 ? 0 : overlap - 3;

                int i3 = canRotate ? (int)(rNum.nextDouble() * 3D) : 0;
                jp[i] = new JigsawPiece(image, overlap, j - ai1[3], k - ai1[0], w + ai1[3] + ai1[1], h + ai1[0] + ai1[2], jPosX, jPosY, l, i1, ai[0], ai[1], ai[2], ai[3], points, i3);
                iOrder[i] = i;
                tracker.addImage(jp[i].getPiece(), i + 1);
                i++;
                j += w;
            }

            k += h;
        }

        cutting = 0;
    }

    public void destroy()
    {
        if(aImg != null)
            aImg.flush();
        if(b1 != null)
            b1.removeActionListener(this);
        if(b2 != null)
            b2.removeActionListener(this);
        if(b3 != null)
            b3.removeActionListener(this);
        if(b4 != null)
            b4.removeActionListener(this);
        removeMouseListener(this);
        removeMouseMotionListener(this);
    }

    public void dloadImage(String s)
    {
        try
        {
            synchronized(this)
            {
                tImg = aImg = getImage(new URL(getDocumentBase(), s)).getScaledInstance(iWidth, iHeight, 2);
                tracker.addImage(tImg, 0);
                if(hlpImgFade > -1)
                    img1 = createImage(new FilteredImageSource(tImg.getSource(), new JigsawCutter(hlpImgFade, hlpImgGray, 0, 0, 0, 0, 0, 0, 0, points)));
                //repaint();
            }
        }
        catch(MalformedURLException malformedurlexception)
        {
            System.out.println("MalformedURLException: " + malformedurlexception);
        }
    }

    private JigsawPiece findPiece(int i, int j, boolean flag)
    {
        for(int k = rows - 1; k >= 0; k--)
        {
            for(int l = cols - 1; l >= 0; l--)
            {
                JigsawPiece jigsawpiece = jp[k * cols + l];
                if(jigsawpiece.isOver(i, j))
                {
                    selID = k * cols + l;
                    return jigsawpiece;
                }
            }

        }

        return null;
    }

    public void init()
    {
        sRunURL = null;
        sRunTarget = null;
        if(aImg != null)
            aImg.flush();
        removeMouseListener(this);
        removeMouseMotionListener(this);
        Cursor cursor = new Cursor(12);
        appW = getBounds().width;
        appH = getBounds().height;
        offScrImg = createImage(appW, appH);
        offScrGr = offScrImg.getGraphics();
        setLayout(new FlowLayout(0, 3, 3));
        t = new String[4];
        t[0] = new String("Breakup");
        t[1] = new String("Tidy Pieces");
        t[2] = new String("Solve");
        t[3] = new String("Jigsaw Solved");
        readParams();
        if(STEP_SIZE == 0)
            STEP_SIZE = makeStepSize(iWidth, iHeight, rows, cols);
        add(b4 = new Button("?"));
        add(b1 = new Button(t[0]));
        add(l2 = new Label(""));
        add(b2 = new Button(t[1]));
        if(showSolve)
        {
            add(b3 = new Button(t[2]));
            b3.setCursor(cursor);
            b3.addActionListener(this);
        }
        add(l1 = new Label(t[3]));
        b1.setCursor(cursor);
        b2.setCursor(cursor);
        b4.setCursor(cursor);
        b4.setFont(new Font("helvetica", 1, 13));
        b4.setForeground(Color.white);
        b4.setBackground(Color.darkGray);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        setBackground(bgColor);
        l1.setBackground(bgColor);
        l2.setBackground(bgColor);
        l1.setForeground(txColor);
        validateKeepClear();
        points = new int[STEP_SIZE + 1];
        int i = 0;
        for(double d = 0.0D; d < 3.1415926535897931D;)
        {
            double d1 = Math.sin(d);
            points[i] = (int)((double)((STEP_SIZE - 2) / 2) * d1);
            d += 3.1415926535897931D / (double)STEP_SIZE;
            i++;
        }

        totalPieces = rows * cols;
        jPosX = appW - iWidth - 7;
        switch(vAlign)
        {
        case 0: // '\0'
            jPosY = 5;
            break;

        case 4: // '\004'
            jPosX = appW / 2 - iWidth / 2;
            // fall through

        case 1: // '\001'
            jPosY = appH / 2 - iHeight / 2;
            break;

        case 2: // '\002'
            jPosY = appH - iHeight - 6;
            break;

        case 3: // '\003'
            jPosY = 34;
            break;
        }
        iNumPieces = rows * cols;
        jp = new JigsawPiece[iNumPieces];
        iOrder = new int[iNumPieces];
        repaint();
    }

    private int makeStepSize(int i, int j, int k, int l)
    {
        byte byte0 = 16;
        int i1 = i / l;
        int j1 = j / k;
        int k1 = i1 <= j1 ? i1 : j1;
        if(k1 < 28)
            byte0 = 10;
        else
        if(k1 < 35)
            byte0 = 12;
        else
        if(k1 < 43)
            byte0 = 14;
        else
        if(k1 < 50)
            byte0 = 16;
        else
        if(k1 < 60)
            byte0 = 18;
        else
        if(k1 < 70)
            byte0 = 20;
        else
        if(k1 < 80)
            byte0 = 22;
        else
            byte0 = 24;
        return byte0;
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
    }

    public void mouseDragged(MouseEvent mouseevent)
    {
        if(selPP != null)
        {
            int i = mouseevent.getX() - xmOff;
            int j = mouseevent.getY() - ymOff;
            if(i < 5)
                i = 5;
            if(i > appW - w - 3)
                i = appW - w - 3;
            if(j < 8)
                j = 8;
            if(j > appH - h - 3)
                j = appH - h - 3;
            selPP.setPosition(i, j);
            repaint();
        }
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mouseMoved(MouseEvent mouseevent)
    {
        JigsawPiece jigsawpiece = selPP;
        selPP = findPiece(mouseevent.getX(), mouseevent.getY(), false);
        if(jigsawpiece != selPP)
            repaint();
    }

    public void mousePressed(MouseEvent mouseevent)
    {
        if((mouseevent.getModifiers() & 0x10) > 0 || !canRotate)
        {
            if(selPP != null)
            {
                xmOff = mouseevent.getX() - selPP.getX();
                ymOff = mouseevent.getY() - selPP.getY();
                if(bCanReorder)
                    reorderList(selID);
            }
            repaint();
        } else
        if((mouseevent.getModifiers() & 4) > 0 && canRotate)
        {
            selPP = findPiece(mouseevent.getX(), mouseevent.getY(), true);
            if(selPP != null)
            {
                selPP.setOrientation(1);
                repaint();
            }
        }
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
        if(selPP != null)
        {
            int i = selPP.getX();
            int j = selPP.getY();
            int k = selPP.getBaseX();
            int l = selPP.getBaseY();
            if(i > k - autoSnap && i < k + autoSnap && j > l - autoSnap && j < l + autoSnap)
                selPP.setPosition(k, l);
        }
        selPP = null;
    }

    public void paint(Graphics g)
    {
        boolean flag = false;
        int j = 0;
        isComplete = true;
        offScrGr.setColor(bgColor);
        offScrGr.fillRect(0, 0, appW, appH);
        offScrGr.setColor(ofColor);
        offScrGr.drawRect(0, 0, appW - 2, appH - 2);
        if(img1 != null)
        {
            offScrGr.drawImage(img1, jPosX, jPosY, this);
        } else
        {
            offScrGr.setColor(bdColor);
            offScrGr.fillRect(jPosX, jPosY, iWidth, iHeight);
        }
        offScrGr.setColor(ifColor);
        offScrGr.drawRect(jPosX - 1, jPosY - 1, iWidth + 2, iHeight + 2);
        for(int i = 0; i < iNumPieces; i++)
        {
            JigsawPiece jigsawpiece = jp[iOrder[i]];
            if(jigsawpiece != null)
            {
                int i1 = jigsawpiece.getX();
                int i2 = jigsawpiece.getY();
                if(tracker.statusID(iOrder[i] + 1, false) == 8)
                {
                    Image image1 = jigsawpiece.getPiece();
                    j++;
                    int k2 = image1.getWidth(this);
                    int j3 = image1.getHeight(this);
                    switch(jigsawpiece.getOrientation())
                    {
                    case 0: // '\0'
                        offScrGr.drawImage(image1, i1, i2, this);
                        break;

                    case 1: // '\001'
                        offScrGr.drawImage(image1, i1, i2, i1 + k2, i2 + j3, k2, 0, 0, j3, this);
                        break;

                    case 2: // '\002'
                        offScrGr.drawImage(image1, i1, i2, i1 + k2, i2 + j3, k2, j3, 0, 0, this);
                        break;

                    case 3: // '\003'
                        offScrGr.drawImage(image1, i1, i2, i1 + k2, i2 + j3, 0, j3, k2, 0, this);
                        break;
                    }
                }
                if(isComplete && (i1 != jigsawpiece.getBaseX() || i2 != jigsawpiece.getBaseY() || jigsawpiece.getOrientation() != 0))
                    isComplete = false;
            }
        }

        if(isComplete && j != iNumPieces)
            isComplete = false;
        if(selPP != null)
        {
            int k = selPP.getX();
            int j1 = selPP.getY();
            if(!bCanReorder || bAutoShow)
            {
                Image image = selPP.getPiece();
                int j2 = image.getWidth(this);
                int i3 = image.getHeight(this);
                switch(selPP.getOrientation())
                {
                case 0: // '\0'
                    offScrGr.drawImage(image, k, j1, this);
                    break;

                case 1: // '\001'
                    offScrGr.drawImage(image, k, j1, k + j2, j1 + i3, j2, 0, 0, i3, this);
                    break;

                case 2: // '\002'
                    offScrGr.drawImage(image, k, j1, k + j2, j1 + i3, j2, i3, 0, 0, this);
                    break;

                case 3: // '\003'
                    offScrGr.drawImage(image, k, j1, k + j2, j1 + i3, 0, i3, j2, 0, this);
                    break;
                }
            }
            offScrGr.setColor(pkColor);
            offScrGr.drawRect(selPP.getX(), selPP.getY(), selPP.getW(), selPP.getH());
        }
        if(isComplete)
        {
            l1.setText(t[3]);
            l1.setVisible(true);
            if(sRunURL != null && !bDoneSolve)
            {
                URL url = null;
                bDoneSolve = true;
                try
                {
                    url = new URL(sRunURL);
                }
                catch(MalformedURLException _ex)
                {
                    try
                    {
                        url = new URL(getDocumentBase(), sRunURL);
                    }
                    catch(MalformedURLException _ex2)
                    {
                        System.out.println("Malformed URL: " + sRunURL);
                    }
                }
                if(url != null)
                    if(sRunTarget != null)
                        getAppletContext().showDocument(url, sRunTarget);
                    else
                        getAppletContext().showDocument(url);
            }
        } else
        {
            bDoneSolve = false;
            l1.setText("");
            l1.setVisible(false);
        }
        if(tracker.statusAll(false) != 8)
        {
            int l = (int)((double)appW - 140D) / 2;
            int k1 = (int)((double)appH - 35D) / 2;
            offScrGr.setFont(new Font("helvetica", 1, 15));
            if(tracker.statusID(0, false) != 8)
            {
                offScrGr.setColor(new Color(0xffdfdf));
                offScrGr.fillRect(l, k1, 140, 70);
                offScrGr.setColor(Color.red);
                offScrGr.drawString("Loading image", l + 15, k1 + 25);
            } else
            {
                offScrGr.setColor(new Color(0xdfdfff));
                offScrGr.fillRect(l, k1, 140, 70);
                offScrGr.setColor(Color.blue);
                offScrGr.drawString("Cutting pieces", l + 15, k1 + 25);
            }
            offScrGr.drawString("Please wait...", l + 22, k1 + 55);
            offScrGr.setColor(Color.black);
            offScrGr.drawRect(l + 1, k1 + 1, 140, 70);
        }
        g.drawImage(offScrImg, 0, 0, this);
        super.paint(g);
    }

    public void randomizePosition(JigsawPiece jigsawpiece, boolean flag)
    {
        int i = iWidth / cols;
        int j = iHeight / rows;
        int k = 0;
        int l = 0;
        do
        {
            k = (int)(rNum.nextDouble() * (double)(appW - i - 20 - overlap)) + 10;
            l = (int)(rNum.nextDouble() * (double)(appH - j - 20 - overlap)) + 10;
        } while(k < jPosX + iWidth + 5 + overlap && k > jPosX - i - 5 && l < jPosY + iHeight + 5 + overlap && l > jPosY - j - 5 && flag);
        jigsawpiece.setPosition(k, l);
    }

    private void readParams()
    {
        imgName = getParameter("Image");
        String s;
        if((s = getParameter("ImgWidth")) != null)
            iWidth = (new Integer(s)).intValue();
        if((s = getParameter("ImgHeight")) != null)
            iHeight = (new Integer(s)).intValue();
        if((s = getParameter("Rows")) != null)
            rows = (new Integer(s)).intValue();
        if((s = getParameter("Cols")) != null)
            cols = (new Integer(s)).intValue();
        if((s = getParameter("DimHelpImage")) != null)
            hlpImgFade = (new Integer(s)).intValue();
        if((s = getParameter("HelpImageGrayed")) != null && s.equals("true"))
            hlpImgGray = true;
        if((s = getParameter("AutoSnap")) != null)
            autoSnap = (new Integer(s)).intValue();
        if((s = getParameter("CanRotate")) != null && s.equals("true"))
            canRotate = true;
        if((s = getParameter("KeepBoardClear")) != null && s.equals("true"))
            keepClear = true;
        if((s = getParameter("BgColor")) != null)
            bgColor = Color.decode(s);
        if((s = getParameter("TextColor")) != null)
            txColor = Color.decode(s);
        if((s = getParameter("OuterFrameColor")) != null)
            ofColor = Color.decode(s);
        if((s = getParameter("InnerFrameColor")) != null)
            ifColor = Color.decode(s);
        if((s = getParameter("BoardColor")) != null)
            bdColor = Color.decode(s);
        if((s = getParameter("SelectColor")) != null)
            pkColor = Color.decode(s);
        if((s = getParameter("BreakupText")) != null)
            t[0] = new String(s);
        if((s = getParameter("TidyText")) != null)
            t[1] = new String(s);
        if((s = getParameter("SolveText")) != null)
            t[2] = new String(s);
        if((s = getParameter("MessageText")) != null)
            t[3] = new String(s);
        if((s = getParameter("RunURL")) != null)
            sRunURL = new String(s);
        if((s = getParameter("RunTarget")) != null)
            sRunTarget = new String(s);
        if((s = getParameter("AllowSolve")) != null && s.equals("false"))
            showSolve = false;
        if((s = getParameter("LosePieces")) != null && s.equals("true"))
            bCanReorder = false;
        if((s = getParameter("AutoShowPieces")) != null && s.equals("false"))
            bAutoShow = false;
        if((s = getParameter("PictureAlign")) != null)
        {
            if(s.equals("top"))
                vAlign = 0;
            if(s.equals("spaced"))
                vAlign = 3;
            if(s.equals("bottom"))
                vAlign = 2;
            if(s.equals("center"))
                vAlign = 4;
        }
        if((s = getParameter("Connector")) != null)
            switch((new Integer(s)).intValue())
            {
            case -1:
                STEP_SIZE = 0;
                break;

            case 0: // '\0'
                STEP_SIZE = 16;
                break;

            case 1: // '\001'
                STEP_SIZE = 18;
                break;

            case 2: // '\002'
                STEP_SIZE = 20;
                break;

            case 3: // '\003'
                STEP_SIZE = 22;
                break;

            case 4: // '\004'
                STEP_SIZE = 24;
                break;
            }
        if(autoSnap < 0)
            autoSnap = 0;
        if(autoSnap > 15)
            autoSnap = 15;
        if(hlpImgFade > 100)
            hlpImgFade = 100;
    }

    private void reorderList(int i)
    {
        boolean flag = false;
        if(i > iNumPieces)
            i = iNumPieces;
        if(iOrder[iNumPieces - 1] != i)
        {
            for(int j = 0; j < iNumPieces - 1; j++)
            {
                if(!flag && iOrder[j] == i)
                    flag = true;
                if(flag)
                    iOrder[j] = iOrder[j + 1];
            }

            if(flag)
                iOrder[iNumPieces - 1] = i;
        }
    }

    public void run()
    {
        Thread thread = Thread.currentThread();
        repaint();
        dloadImage(imgName);
        repaint();
        try
        {
            tracker.waitForID(0);
        }
        catch(InterruptedException _ex)
        {
            return;
        }
        cutupPicture(tImg);
        repaint();
        try
        {
            tracker.waitForAll();
        }
        catch(InterruptedException _ex)
        {
            return;
        }
        breakupPuzzle(false);
        repaint();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void showAbout()
    {
        Label alabel[] = new Label[6];
        Frame frame = new Frame("About");
        frame.setBackground(Color.lightGray);
        frame.setLayout(new FlowLayout(0, 2, 2));
        Panel panel = new Panel();
        frame.add(panel);
        panel.setLayout(new BorderLayout(1, 1));
        Label label;
        panel.add("North", label = new Label(" Jigsaw "));
        label.setFont(new Font("helvetica", 1, 32));
        label.setForeground(Color.yellow);
        label.setBackground(Color.black);
        Panel panel1 = new Panel();
        panel.setBackground(Color.lightGray);
        panel.add("Center", panel1);
        panel1.setLayout(new GridLayout(3, 2, 0, 0));
        panel1.setBackground(Color.lightGray);
        panel1.add(alabel[0] = new Label(""));
        panel1.add(alabel[3] = new Label(""));
        panel1.add(alabel[1] = new Label(""));
        panel1.add(alabel[4] = new Label(""));
        panel1.add(alabel[2] = new Label(""));
        panel1.add(alabel[5] = new Label(""));
        TextArea textarea;
        panel.add("South", textarea = new TextArea("", 6, 37, 1));
        textarea.setBackground(Color.white);
        textarea.setFont(new Font("helvetica", 0, 11));
        textarea.setEditable(false);
        textarea.append("CAPTCHA");

        for(int i = 0; i < 6; i++)
        {
            alabel[i].setFont(new Font("helvetica", 1, 16));
            alabel[i].setBackground(Color.lightGray);
            if(i < 3)
                alabel[i].setForeground(new Color(0, 64, 0));
        }

        frame.setSize(256, 295);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation(150, 100);
        frame.addWindowListener(this);
    }

    public void start()
    {
        runner = new Thread(this);
        runner.start();
    }

    public synchronized void stop()
    {
        runner = null;
    }

    public void update(Graphics g)
    {
        paint(g);
    }

    private void validateKeepClear()
    {
        w = iWidth / cols;
        h = iHeight / rows;
        int i = appW - iWidth - 20 - overlap;
        int j = appH - iHeight - 20 - overlap;
        int k = iWidth / cols;
        int l = iHeight / rows;
        int i1 = k <= l ? l : k;
        if(i1 + 5 > i && i1 + 5 > j)
        {
            keepClear = false;
            b2.setVisible(false);
        }
    }

    public void windowActivated(WindowEvent windowevent)
    {
    }

    public void windowClosed(WindowEvent windowevent)
    {
    }

    public void windowClosing(WindowEvent windowevent)
    {
        Window window = windowevent.getWindow();
        window.dispose();
    }

    public void windowDeactivated(WindowEvent windowevent)
    {
    }

    public void windowDeiconified(WindowEvent windowevent)
    {
    }

    public void windowIconified(WindowEvent windowevent)
    {
    }

    public void windowOpened(WindowEvent windowevent)
    {
    }

    int wSize;
    int hSize;
    int rows;
    int cols;
    int iWidth;
    int iHeight;
    int jPosX;
    int jPosY;
    int appW;
    int appH;
    int hlpImgFade;
    boolean hlpImgGray;
    boolean avoidCenter;
    boolean canRotate;
    boolean keepClear;
    int autoSnap;
    int cutting;
    int ts;
    int overlap;
    int totalPieces;
    int points[];
    int w;
    int h;
    int vAlign;
    Color bgColor;
    Color txColor;
    Color ofColor;
    Color ifColor;
    Color pkColor;
    Color bdColor;
    boolean showSolve;
    Thread runner;
    AppletContext apc;
    boolean bDoneSolve;
    boolean isComplete;
    boolean bImageLoaded;
    MediaTracker tracker;
    int selID;
    boolean bCanReorder;
    boolean bAutoShow;
    Graphics offScrGr;
    Image offScrImg;
    Image img1;
    Image tImg;
    Image aImg;
    String imgName;
    String sRunURL;
    String sRunTarget;
    String t[];
    int iOrder[];
    JigsawPiece jp[];
    JigsawPiece selPP;
    Random rNum;
    int xmOff;
    int ymOff;
    Label l1;
    Label l2;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    int iNumPieces;
    public int STEP_SIZE;
}