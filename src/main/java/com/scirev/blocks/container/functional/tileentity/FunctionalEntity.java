package com.scirev.blocks.container.functional.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class FunctionalEntity extends TileEntity {
	public void onBlockPlaced(World world, int x, int y, int z) {
		// TODO Auto-generated method stub // Those things suck
		if (world.getTileEntity(x + 1, y, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x + 1, y, z)).NeighborBlockCreated(world.getTileEntity(x, y, z),
				        3);
				NeighborBlockCreated(world.getTileEntity(x + 1, y, z), 0);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y + 1, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y + 1, z)).NeighborBlockCreated(world.getTileEntity(x, y, z),
				        4);
				NeighborBlockCreated(world.getTileEntity(x, y + 1, z), 1);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y, z + 1) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y, z + 1)).NeighborBlockCreated(world.getTileEntity(x, y, z),
				        5);
				NeighborBlockCreated(world.getTileEntity(x, y, z + 1), 2);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x - 1, y, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x - 1, y, z)).NeighborBlockCreated(world.getTileEntity(x, y, z),
				        0);
				NeighborBlockCreated(world.getTileEntity(x - 1, y, z), 3);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y - 1, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y - 1, z)).NeighborBlockCreated(world.getTileEntity(x, y, z),
				        1);
				NeighborBlockCreated(world.getTileEntity(x, y - 1, z), 4);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y, z - 1) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y, z - 1)).NeighborBlockCreated(world.getTileEntity(x, y, z),
				        2);
				NeighborBlockCreated(world.getTileEntity(x, y, z - 1), 5);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
	}

	public void onBlockBroke(World world, int x, int y, int z) {
		// TODO Auto-generated method stub // Those things suck
		if (world.getTileEntity(x + 1, y, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x + 1, y, z)).NeighborBlockBroke(world.getTileEntity(x, y, z),
				        3);
				//NeighborBlockBroke(world.getBlock(x + 1, y, z), 0);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y + 1, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y + 1, z)).NeighborBlockBroke(world.getTileEntity(x, y, z),
				        4);
				//NeighborBlockBroke(world.getBlock(x, y + 1, z), 1);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y, z + 1) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y, z + 1)).NeighborBlockBroke(world.getTileEntity(x, y, z),
				        5);
				//NeighborBlockBroke(world.getBlock(x, y, z + 1), 2);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x - 1, y, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x - 1, y, z)).NeighborBlockBroke(world.getTileEntity(x, y, z),
				        0);
				//NeighborBlockBroke(world.getBlock(x - 1, y, z), 3);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y - 1, z) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y - 1, z)).NeighborBlockBroke(world.getTileEntity(x, y, z),
				        1);
				//NeighborBlockBroke(world.getBlock(x, y - 1, z), 4);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
		if (world.getTileEntity(x, y, z - 1) != null) {
			try {
				((FunctionalEntity) world.getTileEntity(x, y, z - 1)).NeighborBlockBroke(world.getTileEntity(x, y, z),
				        2);
				//NeighborBlockBroke(world.getBlock(x, y, z - 1), 5);
			} catch (ClassCastException e) {
				// TODO: handle exception
			}

		}
	}

	public void NeighborBlockCreated(TileEntity which, int direction) {

	}

	public void NeighborBlockBroke(TileEntity which, int direction) {

	}
}
