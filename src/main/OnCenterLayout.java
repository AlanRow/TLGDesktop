package main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class OnCenterLayout implements LayoutManager
{
    private Dimension size = new Dimension();

    @Override
    public void addLayoutComponent(String name, Component comp) {}
    
    @Override
    public void removeLayoutComponent(Component comp) {}

    // ����� ����������� ������������ ������� ��� ����������
    @Override
    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    // ����� ����������� ����������������� ������� ��� ����������
    @Override
    public Dimension preferredLayoutSize(Container c) {
        return new Dimension(300, 60 * c.getComponents().length);
    }
    
    // ����� ������������ ����������� � ����������
    @Override
    public void layoutContainer(Container container)
    {
        // ������ �����������
        Component list[] = container.getComponents();
        int currentY = container.getHeight() / 2;
        int alignX = container.getWidth() / 2;
        for (int i = 0; i < list.length; i++) {
            // ����������� ����������������� ������� ����������
            Dimension pref = list[i].getPreferredSize();
            // ���������� ���������� �� ������
            list[i].setBounds(alignX - (int)pref.getWidth()/2, currentY, pref.width, pref.height);
            // ��������� ���������� � 5 ��������
            currentY += pref.height + 10;
        }
    }
    // ����� ���������� ������������ ������� ����������
    private Dimension calculateBestSize(Container c)
    {
        // ���������� ����� ����������
        Component[] list = c.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < list.length; i++) {
            int width = list[i].getWidth();
            // ����� ���������� � ������������ ������
            if ( width > maxWidth ) 
                maxWidth = width;
        }
        // ������ ���������� � ����� � ������ ������ �������
        size.width = maxWidth;
        // ���������� ������ ����������
        int height = 0;
        for (int i = 0; i < list.length; i++) {
            height += 10;
            height += list[i].getHeight();
        }
        size.height = height;
        return size;
    }
}