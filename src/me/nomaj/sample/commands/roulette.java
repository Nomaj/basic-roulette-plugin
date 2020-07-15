package me.nomaj.sample.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.nomaj.sample.Main;
import net.md_5.bungee.api.ChatColor;

public class roulette implements CommandExecutor {
	@SuppressWarnings("unused")
	private Main plugin;
	
	public roulette(Main plugin) {
		this.plugin = plugin;
		plugin.getCommand("roulette").setExecutor(this);
	}
	
	/* *********************************************************************************************************************
	 * 
	 * Function Description: after /r or /roulette, freeze, blind, and display message to all players on the server. 
	 * Annihilate one player. 5s later, clear all potion effects.
	 * 
	 * *********************************************************************************************************************/
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player gamer = (Player) sender;
		if (gamer.hasPermission("roulette.use")) {
			Player [] players = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
			PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, 10, 100);
			PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 10, 100);
			for (Player p : players) {
				p.addPotionEffect(blind);
				p.addPotionEffect(slow);
				p.sendMessage("Roulette incoming!");
				delay(p);
			}
			Random r = new Random();
			Player dead = players[r.nextInt(players.length)];
			dead.setHealth(0);
			return true;
		} else {
			gamer.sendMessage("You do not have permission to execute this statement");
		}
		return false;
	}
	
	/* *********************************************************************************************************************
	 * 
	 * Function Description: if the player is still alive, display chat message and clear potion effects after 5 seconds.
	 * 
	 * *********************************************************************************************************************/
	public void delay(Player player) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
			public void run() {
				if (player.getHealth() > 0) {
					player.sendMessage(ChatColor.GREEN + "You survived!");
					player.getActivePotionEffects().clear();
				}
			}
		}, 100L);
	}
}