package CodeWars.engine.client;
import CodeWars.engine.Runner;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Client
{
        UI ui;
        Map map;

        public Client(Runner runner)
        {
            ui = new UI(runner.getSize());
            Map map = new Map(ui);
        }

    }