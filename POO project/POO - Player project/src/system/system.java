package system;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import player.player;

public class system {

    public player initializePlayer(Scanner scanner) {

        System.out.println("Nome: ");
        String name = scanner.nextLine();

        System.out.println("Vida: ");
        float health = scanner.nextFloat();

        System.out.println("Poder de ataque: ");
        float atkPower = scanner.nextFloat();

        String[] inventory = { "banana", "luva", "anel", "capacete" };

        player player = new player(name, health, atkPower, inventory);
        return player;
    }

    public void clearConsole() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void combat(player player, player target, Scanner scanner) throws InterruptedException {

        while (player.health > 0 && target.health > 0) {
            clearConsole();
            scanner.nextLine();
            Random rand = new Random();

            System.out.println("-==============================- \n\n"
                    + "PLAYER \n\n"
                    + "Nome: " + player.name + "\n\n"
                    + "Vida: " + player.health + "\n");

            System.out.println("-==============================- \n");

            // PRINT NPC's STATUS

            System.out.println("NPC \n\n"
                    + "Nome: " + target.name + "\n\n"
                    + "Vida: " + target.health + "\n");

            System.out.println("-==============================- \n");

            System.out.println("O que você irá fazer? \n"
                    + "1 - Atacar (causar dano em seu inimigo) \n"
                    + "2 - Descansar (restaura 20% de sua vida, mas você perde o turno) \n"
                    + "3 - Nada (nada...) \n");

            int combatIndex = scanner.nextInt();

            switch (combatIndex) {
                case 1: {
                    player.atk(target);
                    break;
                }

                case 2: {
                    player.rest();
                    break;
                }

                case 3: {
                    clearConsole();

                    System.out.println("Nada...........");
                    TimeUnit.SECONDS.sleep(2);
                    break;
                }

                default: {
                    clearConsole();

                    System.out.println("======= Escolha inválida, perdeu o turno!!! =======");

                    TimeUnit.SECONDS.sleep(2);
                    break;
                }
            }

            if (target.health <= 0 || player.health <= 0) {
                break;
            }

            if (target.health <= target.maxHealth * .5f && (rand.nextInt(10) + 1) > 6) {
                target.rest();
            } else {
                target.atk(player);
            }

        }

        clearConsole();

        if (target.health <= 0) {
            System.out.println("Combate finalizado! Campeão: " + player.name);
        } else {
            System.out.println("Combate finalizado! Campeão: " + target.name);
        }

        TimeUnit.SECONDS.sleep(2);

    }

    public void trade(player player, player target, Scanner scanner) throws InterruptedException {
        clearConsole();
        scanner.reset();

        System.out.println("-==============================- \n\n"
                + "PLAYER \n\n"
                + "Nome: " + player.name + "\n\n"
                + "Inventário: ");

        for (int i = 0; i < player.inventory.length; i++) {
            System.out.print(player.inventory[i] + ", ");
        }
        System.out.println("\n\n"
                + "-==============================- \n");

        // PRINT NPC's STATUS

        System.out.println("NPC \n\n"
                + "Nome: " + target.name + "\n\n"
                + "Inventário: ");

        for (int i = 0; i < target.inventory.length; i++) {
            System.out.print(target.inventory[i] + ", ");
        }
        System.out.println("\n\n"
                + "-==============================- \n");

        System.out.println("Digite o exato nome do item que deseja transferir para o alvo: ");

        scanner.nextLine();
        String itemDesejado = scanner.nextLine();
        player.trade(target, itemDesejado);

    }
}
