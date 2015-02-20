package de.zerian.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Warps
{
  private static File file = new File("plugins/KurzyArmy/Warp", "warps.yml");
  private static FileConfiguration config = YamlConfiguration.loadConfiguration(file);
  
  public static void load()
  {
    try
    {
      config.load(file);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    catch (InvalidConfigurationException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void save()
  {
    try
    {
      config.save(file);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static void create()
  {
    if (!file.exists())
    {
      save();
    }
  }
  
  public static FileConfiguration get()
  {
    return config;
  }
  }
