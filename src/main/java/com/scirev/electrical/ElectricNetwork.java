package com.scirev.electrical;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;

public class ElectricNetwork {

	private static ElectricNetwork instance = new ElectricNetwork();

	public ElectricNetwork() {
		// TODO Auto-generated constructor stub
	}

	public static ElectricNetwork getInstance() {
		return instance;
	}

	public Map<ChunkCoordinates, Integer> powers = new HashMap<ChunkCoordinates, Integer>();

	public void loadTileEntity(TileEntity te, int power) {
		ChunkCoordinates cc = new ChunkCoordinates(te.xCoord, te.yCoord, te.zCoord);
		if (!containsKey(cc)) {
			powers.put(cc, power);
		}
	}

	public void addTileEntity(TileEntity te) {
		ChunkCoordinates cc = new ChunkCoordinates(te.xCoord, te.yCoord, te.zCoord);
		if (!containsKey(cc)) {
			powers.put(cc, 0);
		}
	}

	public void removeTileEntity(TileEntity te) {
		ChunkCoordinates cc = new ChunkCoordinates(te.xCoord, te.yCoord, te.zCoord);
		remove(cc);
	}

	public void addPower(TileEntity te, int power) {
		int fpower = getPower(new ChunkCoordinates(te.xCoord, te.yCoord, te.zCoord)) + power;
		removeTileEntity(te);
		loadTileEntity(te, fpower);
	}

	public void removePower(TileEntity te, int power) {
		int fpower = getPower(new ChunkCoordinates(te.xCoord, te.yCoord, te.zCoord)) - power;
		removeTileEntity(te);
		loadTileEntity(te, fpower);
	}

	public void setPower(TileEntity te, int power) {
		int fpower = power;
		removeTileEntity(te);
		loadTileEntity(te, fpower);
	}

	public int getPower(TileEntity te) {
		try {
			return getPower(new ChunkCoordinates(te.xCoord, te.yCoord, te.zCoord));
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean containsKey(ChunkCoordinates key) {
		Set<ChunkCoordinates> set = powers.keySet();
		for (ChunkCoordinates cc : set) {
			if (cc.equals(key)) {
				return true;
			}
		}
		return false;
	}

	private int getPower(ChunkCoordinates key) {
		Set<ChunkCoordinates> set = powers.keySet();
		for (ChunkCoordinates cc : set) {
			if (cc.equals(key)) {
				return powers.get(cc);
			}
		}
		return 0;
	}

	public void remove(ChunkCoordinates key) {
		Set<ChunkCoordinates> set = powers.keySet();
		ChunkCoordinates target = null;
		for (ChunkCoordinates cc : set) {
			if (cc.equals(key)) {
				target = cc;
				break;
			}
		}

		powers.remove(target);
	}

}
