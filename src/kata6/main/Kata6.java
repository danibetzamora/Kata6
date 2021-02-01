package kata6.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata6.control.Command;
import kata6.control.DownCommand;
import kata6.control.LeftCommand;
import kata6.control.RightCommand;
import kata6.control.UpCommand;
import kata6.model.Block;

public class Kata6 extends JFrame {
    
    private BlockPanel blockDisplay;
    private final Map<String, Command> commands = new HashMap<>();
    
    public static void main(String[] args) {
        new Kata6().execute();
    }
   
    public Kata6() {
        this.setTitle("Block Shifter");
        this.setSize(700, 762);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
        
        Block block = new Block(4, 4);
        this.blockDisplay.display(block);
        this.commands.put("left", new LeftCommand(block));
        this.commands.put("right", new RightCommand(block));
        this.commands.put("up", new UpCommand(block));
        this.commands.put("down", new DownCommand(block));
    }

    private void execute() {
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel blockPanel = new BlockPanel();
        this.blockDisplay = blockPanel;
        return blockPanel;
    }

    private JMenuBar toolbar() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(button("left"));
        menubar.add(button("up"));
        menubar.add(button("down"));
        menubar.add(button("right"));
        return menubar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                commands.get(name).execute();
            }
            
        });
        return button;
    }
    
}
