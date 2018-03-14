# SummonerWarBot

This is a project to automate the mobile game Summoners War on the Android platform.

## Usage

You need an Android phone, and a PC with Android Debug Bridge (ADB) installed as a command accessible from the default terminal.

The PC (Host) is typically linked with the Android phone (Target) with a USB to micro USB cable, but ADB over WiFi does exist, but hasn't been tested with this program.

## Main Goal

The one thing that needs to be completed for this project to be considered good.

1. Automate daily quests.

### Side Goals

These goals are not really important, but would be good to implement after the Main Goal is completed.
 
1. Collect mana stones and gems from the different buildings that produce them.
2. When using the Power-Up Circle, only use silver star monsters.
3. Use up energy to perform tasks based on what is needed/lacking.
4. Be able to start from a new account, proceed through the tutorial properly, and run the account by itself with no human help.

## Methods

To know what is happening on the screen, currently the script takes a screenshot using the Android command "screencap" with a PNG format. It then reads the pixel at a certain coordinate on the screen, and using a array of Hex values located in ArrayHolder.HEXCOLOR, decides if to tap the screen at certain coordinates or not,

Currently working on this: Instead of querying for colors of specific pixels on the screen and deciding what to do based on that, I want it to scan the entirety of the screenshot and logically make decisions based on what it finds, with the overall goal still completing all the daily quests. 

### Bottlenecks

These are known bottlenecks that slow down either portions or the entire script.

1. Using screencap to make a PNG file of the Targets screen is slow.
2. Transferring the PNG image from the Target to the Host is slow (USB 2.0).

### Issues

1. Currently only have a Galaxy S6, with a 1440x2560 (portrait) screen. Anything else will almost certainly break the script, particularly the hex values. 

### Fixes

These applies to both Bottlenecks and Issues. Not required, but would be good to fix.

1. Switch from screepcap to something faster
2. Find method to either shrink the size of image transferred, or increase rate of transfer.


