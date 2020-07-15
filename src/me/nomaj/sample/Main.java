package me.nomaj.sample;

import org.bukkit.plugin.java.JavaPlugin;

import me.nomaj.sample.commands.roulette;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		new roulette(this);
	}
}
