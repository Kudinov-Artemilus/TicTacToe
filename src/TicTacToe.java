import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_O = 200;
    int[][] field;
    boolean isXturn;
  public TicTacToe() {
      enableEvents(AWTEvent.MOUSE_EVENT_MASK);
      field = new int[3][3];
      initGane();
  }
  public void initGane() {
      for (int i = 0; i < 3; ++i) {
          for (int j = 0; j < 3; ++j) {
              field[i][j] = FIELD_EMPTY;
          }
      }
      isXturn = true;
  }
    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
      super.processMouseEvent(mouseEvent);
      if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
          int x = mouseEvent.getX();
          int y = mouseEvent.getY();
          int i = (int) ((float) x / getWidth() * 3);
          int j = (int) ((float) y / getHeight() * 3);
          if (field[i][j] == FIELD_EMPTY) {
              field[i][j] = isXturn?FIELD_X:FIELD_O;
              isXturn = !isXturn;
              repaint();
              int res = checkState();
              if(res != 0) {
                  if(res == FIELD_O * 3) {
                      JOptionPane.showMessageDialog(this, "нолики выйграли!", "Победа!", JOptionPane.INFORMATION_MESSAGE);
                  } else if(res == FIELD_X * 3) {
                      JOptionPane.showMessageDialog(this, "крестики выйграли!", "Победа!", JOptionPane.INFORMATION_MESSAGE);

                  } else {
                      JOptionPane.showMessageDialog(this, "ничья", "Ничья!", JOptionPane.INFORMATION_MESSAGE);
                  }
                  initGane();
                  repaint();
              }
          }
      }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
       // graphics.setColor(Color.RED);
       // graphics.drawOval(10,10,100,100);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(graphics);
        drawXO(graphics);


    }

    void drawGrid(Graphics graphics) {
        int w = getWidth();
        int h = getHeight();
        int dw = w / 3;
        int dh = h / 3;
        graphics.setColor(Color.BLACK);
        for (int i = 1; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }
    }
    void drawX (int i, int j, Graphics graphics) {
      graphics.setColor(Color.BLUE);
      int dw = getWidth() / 3;
      int dh = getHeight() / 3;
      int x = i * dw;
      int y = j * dh;
      graphics.drawLine(x, y,x + dw, y+dh);
      graphics.drawLine(x, y + dh, x + dw, y);


    }
    void drawO(int i, int j, Graphics graphics) {
      graphics.setColor(Color.RED);
      int dw = getWidth() / 3;
      int dh = getHeight() / 3;
      int x = i * dw;
      int y = j * dh;
      graphics.drawOval(x + 5 * dw / 100, y, dw * 9 / 10, dh);
    }
    void drawXO(Graphics graphics) {
      for (int i = 0; i < 3; ++i) {
          for (int j = 0; j < 3; ++j) {
              if (field[i][j] == FIELD_X) {
                  drawX(i, j, graphics);
              } else {
                  if(field[i][j] == FIELD_O) {
                      drawO(i, j, graphics);
                  }
              }
          }
      }
    }
int checkState() {
      int diag = 0;
      int diag2 = 0;
      for (int i = 0; i < 3; i++) {
          diag += field[i][i];
          diag2 += field[i][2 - i];

      }
      if (diag == FIELD_O * 3 || diag == FIELD_X * 3) {return diag;}
      if (diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3) {return diag2;}
      int check_i, check_j;
      boolean hasEmpty = false;
      for (int i = 0; i < 3; i++) {
          check_i = 0;
          check_j = 0;
          for (int j = 0; j < 3; j++) {
              if(field[i][j] == 0) {
                  hasEmpty = true;
              }
              if(check_i == FIELD_O * 3 || check_i == FIELD_X * 3) { return check_i;}
              if(check_j == FIELD_O * 3 || check_j == FIELD_X * 3) { return check_j;}

          }
      }
      if(hasEmpty) return 0; else return -1;
}

}
