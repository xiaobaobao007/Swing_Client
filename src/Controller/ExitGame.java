package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import ChooseChapter.ChooseChapter;
import ChooseChapter.ChooseLeave;
import Client.ClientStart;
import GameStart.GameStart;
import Panel.MesPanel;

@SuppressWarnings("serial")
public class ExitGame extends JPanel implements MouseListener {

    public static int width = 455;
    public static int height = 520;
    private GameController gameContrller;
    private int exit_change = 14;

    public ExitGame(GameController gameContrller) {
        super.addMouseListener(this);// 加入mouse监听
        this.gameContrller = gameContrller;
    }

    private static void Return_Choose() {
    	
        ChooseLeave.changeState(GameController.own_cilent_id, 0);
        GameStart.TF = true;
        ClientStart.OutStreamAll("0:0304");
        GameController.restNumbers();
        ChooseChapter.restNumbers();
        GameController.minleave=0;
        MesPanel.messages = new ArrayList <>();
    }

    public void mouseClicked(MouseEvent e)// 鼠标单击事件
    {
        if (e.getButton() == MouseEvent.BUTTON1)// 判断是鼠标左键按下
        {
            Return_Choose();
        }
    }

    public void mouseEntered(MouseEvent e)// 鼠标进入组件
    {
        gameContrller.AddExit(exit_change);
    }

    public void mouseExited(MouseEvent e)// 鼠标退出组件
    {
        gameContrller.SubExit(exit_change);
    }

    public void mousePressed(MouseEvent e)// 鼠标按下
    {
    }

    public void mouseReleased(MouseEvent e)// 鼠标松开
    {
    }

}
