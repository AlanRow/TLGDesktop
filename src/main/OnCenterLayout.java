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

    // Метод определения минимального размера для контейнера
    @Override
    public Dimension minimumLayoutSize(Container c) {
        return calculateBestSize(c);
    }
    // Метод определения предпочтительного размера для контейнера
    @Override
    public Dimension preferredLayoutSize(Container c) {
        return new Dimension(300, 60 * c.getComponents().length);
    }
    
    // Метод расположения компонентов в контейнере
    @Override
    public void layoutContainer(Container container)
    {
        // Список компонентов
        Component list[] = container.getComponents();
        int currentY = container.getHeight() / 2;
        int alignX = container.getWidth() / 2;
        for (int i = 0; i < list.length; i++) {
            // Определение предпочтительного размера компонента
            Dimension pref = list[i].getPreferredSize();
            // Размещение компонента на экране
            list[i].setBounds(alignX - (int)pref.getWidth()/2, currentY, pref.width, pref.height);
            // Учитываем промежуток в 5 пикселов
            currentY += pref.height + 10;
        }
    }
    // Метод вычисления оптимального размера контейнера
    private Dimension calculateBestSize(Container c)
    {
        // Вычисление длины контейнера
        Component[] list = c.getComponents();
        int maxWidth = 0;
        for (int i = 0; i < list.length; i++) {
            int width = list[i].getWidth();
            // Поиск компонента с максимальной длиной
            if ( width > maxWidth ) 
                maxWidth = width;
        }
        // Размер контейнера в длину с учетом левого отступа
        size.width = maxWidth;
        // Вычисление высоты контейнера
        int height = 0;
        for (int i = 0; i < list.length; i++) {
            height += 10;
            height += list[i].getHeight();
        }
        size.height = height;
        return size;
    }
}