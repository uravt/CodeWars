package CodeWars.engine.client;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Client
{
        UI ui;
        Map map;

        public Client()
        {
            ui = new UI();
            Map map = new Map(ui);
        }
    }