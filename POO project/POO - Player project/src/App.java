import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import player.player;
import system.system;

public class App {
    public static void main(String[] args) throws Exception {

        system sys = new system();
        boolean sysRunning = true;

        Scanner scanner = new Scanner(System.in);

        // INITIALIZING PLAYER AND BOT OBJECTS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        sys.clearConsole();

        System.out.println("Antes de começarmos precisamos criar um jogador: \n");

        player player = sys.initializePlayer(scanner);

        String[] npcInventory = { "macaco", "bota", "tira" };
        player npc = new player("Bot_404", 100, 15, npcInventory);

        // MAIN LOOP
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        while (sysRunning) {
            sys.clearConsole();

            // PRINT PLAYER's STATUS

            System.out.println("-==============================- \n\n"
                    + "PLAYER \n\n"
                    + "Nome: " + player.name + "\n\n"
                    + "Vida: " + player.health + "\n\n"
                    + "Inventário: ");

            for (int i = 0; i < player.inventory.length; i++) {
                System.out.print(player.inventory[i] + ", ");
            }
            System.out.println("\n\n"
                    + "-==============================- \n");

            // PRINT NPC's STATUS

            System.out.println("NPC \n\n"
                    + "Nome: " + npc.name + "\n\n"
                    + "Vida: " + npc.health + "\n\n"
                    + "Inventário: ");

            for (int i = 0; i < npc.inventory.length; i++) {
                System.out.print(npc.inventory[i] + ", ");
            }
            System.out.println("\n\n"
                    + "-==============================- \n");

            System.out.println("Escolha uma ação: \n");
            System.out.println("1 - Combate \n"
                    + "2 - Trade de item para o Bot_404 \n"
                    + "3 - Sair... \n");

            int menuIndex = scanner.nextInt();

            switch (menuIndex) {
                case 1: {
                    sys.combat(player, npc, scanner);
                    break;
                }

                case 2: {
                    sys.trade(player, npc, scanner);
                    break;
                }

                case 3: {
                    sys.clearConsole();

                    System.out.println("Até a próxima!");

                    sysRunning = false;
                    break;
                }

                default: {
                    sys.clearConsole();

                    System.out.println("======= Escolha inválida, tente de novo!!! =======");

                    TimeUnit.SECONDS.sleep(2);
                    break;
                }
            }

        }

        scanner.close();
    }
}
