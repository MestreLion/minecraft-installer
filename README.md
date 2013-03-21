Minecraft Installer
===================

A Minecraft installer for Ubuntu, including launcher, icon, settings, etc. Also support uninstall.

Usage
-----

Simply clone the repository or download `install`, make it executable (if needed), and run it on a terminal.

In other words:

	git clone https://github.com/MestreLion/minecraft.git
	cd minecraft
Or

	cd /path/where/you/downloaded/install
	chmod +x install # to make it executable

And finally:

	./install


How it works
------------

Short version:

- Verify if java is installed, and offer to install it
- Download current `minecraft.jar` from Minecraft server
- Create a one-liner `minecraft` shell script launcher for it
- Extract the official icon from inside the `jar`
- Create a `minecraft.desktop` GUI launcher for dashboard, Unity launcher, etc

Long version:

- Show a confirmation screen presenting all settings prior to install
- Verify if `java` command is available
- if not offer to apt-get install `default-jre` and `icedtea-plugin` packages, which currently points to OpenJDK 6
- The jar, icon and desktop files are will be all saved to (or created in):

	`~/.local/share/minecraft`

- If the above directory already exists, ask if it should be deleted first
- Download [https://s3.amazonaws.com/MinecraftDownload/launcher/minecraft.jar](https://s3.amazonaws.com/MinecraftDownload/launcher/minecraft.jar)
- Extract `net/minecraft/favicon.png` icon from `minecraft.jar` using `unzip`
- Create a `minecraft.desktop` file
- Install icon and desktop file using `xdg-icon-resource` and `xdg-desktop-menu`
- Create the shell script `~/.local/share/bin/minecraft` with:

	`java -Xmx1024M -Xms512M -cp "$jar" net.minecraft.LauncherFrame`

- If `~/.local/share/bin` is not in user's `PATH` (it usually is not), add a script snippet to prepend it in `~/.profile`. After the install finishes, suggest user to log out and back in so the new `PATH` is applied


Options
-------

The installer is quite flexible and allows customization via command-line options:


**`-h|--help`**

	Show the help page.

**`-q|--quiet`**

	supress informative messages.

**`--exec=NAME`**

	executable name, also used as a prefix for naming the install directory,
	icon and .desktop files. Default is "minecraft"

**`--prefix=DIR`**

	parent of the install directory. Default is "/home/<user>/.local"

**`--name=NAME`**

	friendly application name, for menu entries. Default is "Minecraft"

**`--custom-icon=FILE [--icon-size=SIZE]`**

	Use the custom icon FILE. If ImageMagik is not installed, icon SIZE
	(32, 48, 64, etc) must be provided.

**`--jar=FILE`**

	the jar launcher for the game. If none is provided, the official, most
	recent one will be downloaded from the Minecraft website:
	https://s3.amazonaws.com/MinecraftDownload/launcher/minecraft.jar

**`--uninstall`**

	Uninstall Minecraft. Combine with --exec and --prefix to
	uninstall from a custom install



Bugs, Wishlist, and Contribuitions
----------------------------------

Read the code for updated `FIXME`and `TODO` notes

Suggestions and improvements are always welcome. Fork it, hack it, and request
a pull! Or use the Issues bug-tracking system in github


Credits
-------

Inspired and adapted from various tutorials found on the internet, including:

- Clay Smalley's [minecraft-installer.deb](http://devio.us/~clorow/)
- [Ubuntu Forums](http://ubuntuforums.org/showthread.php?t=1726735)
- [Minecraft Official forums](http://www.minecraftforum.net/forum/154-tutorials-and-faqs/)


Disclaimers
-----------

***This is in NOT an official Minecraft installer or launcher, and it is NOT in any way affiliated with Mojang or any Minecraft developer or producer!***

And while I am not redistributing anyone else's files along with this project, the installer, as a convenience, does download the official Minecraft files from Mojang's servers at run time. It does not edit or change the original files in any way, it simply downloads the official client and sets the environment to properly run the untouched, unmodified, original Minecraft client.

I created this for my personal use only, and it works great for me on my Ubuntu 12.04 Precise Pangoling 64 bits. You milage may vary, so use this at
own risk.

The copyright and licence notice below applies to this installer, launcher and
associated files *only*. Files downloaded using this installer, such as the
Minecraft official client, are (presumably) copyrighted by Mojang and licensed to use under their own (presumably proprietary) license and terms.

    Copyright (C) 2012 Rodrigo Silva (MestreLion) <linux@rodrigosilva.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. See <http://www.gnu.org/licenses/gpl.html>
