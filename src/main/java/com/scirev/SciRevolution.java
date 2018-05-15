package com.scirev;

import java.util.ArrayList;
import java.util.List;

import com.scirev.blocks.AluminumShellBlock;
import com.scirev.blocks.InsulationStone;
import com.scirev.blocks.IronShellBlock;
import com.scirev.blocks.container.functional.BlastFurnace;
import com.scirev.blocks.container.functional.Bowl;
import com.scirev.blocks.container.functional.Cable;
import com.scirev.blocks.container.functional.CutRubberLog;
import com.scirev.blocks.container.functional.ElectroFurnace;
import com.scirev.blocks.container.functional.Extrusioner;
import com.scirev.blocks.container.functional.Generator;
import com.scirev.blocks.container.functional.LatheBottomShell;
import com.scirev.blocks.container.functional.LatheManipulatePanel;
import com.scirev.blocks.container.functional.LathePowerSource;
import com.scirev.blocks.container.functional.Macerator;
import com.scirev.blocks.container.functional.SteamEngine;
import com.scirev.blocks.container.functional.WoodenPipe;
import com.scirev.blocks.container.functional.tileentity.BlastFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.BowlEntity;
import com.scirev.blocks.container.functional.tileentity.CableEntity;
import com.scirev.blocks.container.functional.tileentity.ElectroFurnaceEntity;
import com.scirev.blocks.container.functional.tileentity.ExtrusionerTileEntity;
import com.scirev.blocks.container.functional.tileentity.GeneratorEntity;
import com.scirev.blocks.container.functional.tileentity.LatheBottomShellEntity;
import com.scirev.blocks.container.functional.tileentity.LatheManipulatePanelEntity;
import com.scirev.blocks.container.functional.tileentity.LathePowerSourceEntity;
import com.scirev.blocks.container.functional.tileentity.MaceratorTileEntity;
import com.scirev.blocks.container.functional.tileentity.SteamEngineEntity;
import com.scirev.blocks.container.functional.tileentity.WoodenPipeEntity;
import com.scirev.blocks.models.ModelLatheBottomShell;
import com.scirev.blocks.models.ModelLatheManipulatePanel;
import com.scirev.blocks.models.ModelLathePowerSource;
import com.scirev.blocks.models.ModelPipe;
import com.scirev.blocks.models.tileentity.RenderBowl;
import com.scirev.blocks.models.tileentity.RenderCable;
import com.scirev.blocks.models.tileentity.RenderLBS;
import com.scirev.blocks.models.tileentity.RenderLMP;
import com.scirev.blocks.models.tileentity.RenderLPS;
import com.scirev.blocks.models.tileentity.RenderPipe;
import com.scirev.blocks.models.tileentity.RenderSE;
import com.scirev.blocks.nature.GeneralLeaf;
import com.scirev.blocks.nature.GeneralLog;
import com.scirev.blocks.nature.GeneralSapling;
import com.scirev.blocks.nature.LeafItems;
import com.scirev.blocks.nature.LogItems;
import com.scirev.blocks.nature.SaplingItems;
import com.scirev.blocks.ore.AluminumOre;
import com.scirev.blocks.ore.CopperOre;
import com.scirev.blocks.ore.MagnetOre;
import com.scirev.blocks.ore.gen.OreGenerator;
import com.scirev.blocks.ore.gen.TreeGenerator;
import com.scirev.items.BowlItem;
import com.scirev.items.CableItem;
import com.scirev.items.render.GenericItemRenderer;
import com.scirev.recipe.AdvRecipe;
import com.scirev.recipe.AdvShapelessRecipe;
import com.scirev.recipe.BlastFurnaceRecipe;
import com.scirev.recipe.ExtrusionerCraftingRecipe;
import com.scirev.recipe.MaceratorCraftingRecipe;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.ExistingSubstitutionException;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.Type;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;

@Mod(modid = SciRevolution.MODID, version = SciRevolution.VERSION)
public class SciRevolution {

	public static final String MODID = "scirev";
	public static final String VERSION = "1.0";
	//Blocks
	public static SteamEngine se = (SteamEngine) new SteamEngine().setBlockName("SteamEngine").setHardness(2f);
	public static Macerator mac = (Macerator) new Macerator(false).setBlockName("Macerator").setHardness(2f);
	public static Macerator lit_mac = (Macerator) new Macerator(true).setBlockName("Lit_Macerator").setHardness(2f)
	        .setLightLevel(15).setLightOpacity(15);
	public static Extrusioner ex = (Extrusioner) new Extrusioner(false).setBlockName("Extrusioner").setHardness(2f);
	public static Extrusioner lit_ex = (Extrusioner) new Extrusioner(true).setBlockName("Lit_Extrusioner")
	        .setHardness(2f);
	public static Generator gen = (Generator) new Generator(false).setBlockName("Generator").setHardness(2f);
	public static Generator lit_gen = (Generator) new Generator(true).setBlockName("Lit_Generator").setHardness(2f)
	        .setLightLevel(15).setLightOpacity(15);
	public static BlastFurnace bf = (BlastFurnace) new BlastFurnace(false).setBlockName("BlastFurnace")
	        .setHardness(2f);
	public static BlastFurnace lit_bf = (BlastFurnace) new BlastFurnace(true).setBlockName("Lit_BlastFurnace")
	        .setHardness(2f).setLightLevel(15).setLightOpacity(15);
	public static ElectroFurnace ef = (ElectroFurnace) new ElectroFurnace(false).setBlockName("ElectroFurnace")
	        .setHardness(2f);
	public static ElectroFurnace lit_ef = (ElectroFurnace) new ElectroFurnace(true).setBlockName("Lit_ElectroFurnace")
	        .setHardness(2f).setLightLevel(15).setLightOpacity(15);

	public static LatheManipulatePanel lmp = (LatheManipulatePanel) new LatheManipulatePanel()
	        .setBlockName("LatheManipulatePanel").setHardness(2f);
	public static LatheBottomShell lbs = (LatheBottomShell) new LatheBottomShell().setBlockName("LatheBottomShell")
	        .setHardness(2f);
	public static LathePowerSource lps = (LathePowerSource) new LathePowerSource().setBlockName("LathePowerSource")
	        .setHardness(2f);

	public static Block ore_copperblock = new CopperOre(Material.rock).setBlockName("CopperOre")
	        .setBlockTextureName("scirev:ore_copper").setHardness(2f);
	public static Block ore_alblock = new AluminumOre(Material.rock).setBlockName("AluminumOre")
	        .setBlockTextureName("scirev:ore_aluminum").setHardness(2f);
	public static Block ore_magblock = new MagnetOre(Material.rock).setBlockName("MagnetOre")
	        .setBlockTextureName("scirev:ore_magnet").setHardness(2f);
	public static Cable cable = new Cable();

	public static Block insulationstone = new InsulationStone().setBlockName("InsulationStone").setHardness(1.5f)
	        .setResistance(6f).setBlockTextureName("scirev:insulationstone");

	public static Block alshellblock = new AluminumShellBlock().setBlockName("AluminumShellBlock").setHardness(2f)
	        .setResistance(6f).setBlockTextureName("scirev:alshellblock");
	public static Block ironshellblock = new IronShellBlock().setBlockName("IronShellBlock").setHardness(2f)
	        .setResistance(6f).setBlockTextureName("scirev:ironshellblock");

	public static Block generallog = new GeneralLog().setBlockName("GeneralLog").setBlockTextureName("scirev:log")
	        .setHardness(0.5f);
	public static Block generalleave = new GeneralLeaf().setBlockName("GeneralLeaf").setBlockTextureName("scirev:leaf");
	public static Block generalsapling = new GeneralSapling().setBlockName("GeneralSapling")
	        .setBlockTextureName("scirev:sapling");

	public static Block cutrubberlog = new CutRubberLog(true).setBlockName("CutRubberLog").setHardness(0.5f);
	public static Block norubberlog = new CutRubberLog(false).setBlockName("NoRubberLog").setHardness(0.5f);
	public static Block woodenpipe = new WoodenPipe().setBlockName("WoodenPipe").setHardness(0.5f);
	public static Block bowl = new Bowl().setBlockName("Bowl").setHardness(0.5f);
	//Items
	public static Item alingot = new Item().setUnlocalizedName("AluminumIngot").setTextureName("scirev:aluminum_ingot");
	public static Item copperingot = new Item().setUnlocalizedName("CopperIngot").setTextureName("scirev:copper_ingot");
	public static Item magnet = new Item().setUnlocalizedName("Magnet").setTextureName("scirev:magnet");
	public static Item steel_ingot = new Item().setUnlocalizedName("SteelIngot").setTextureName("scirev:steel_ingot");

	public static Item ironpowder = new Item().setUnlocalizedName("IronPowder").setTextureName("scirev:ironpowder");
	public static Item copperpowder = new Item().setUnlocalizedName("CopperPowder")
	        .setTextureName("scirev:copperpowder");
	public static Item alpowder = new Item().setUnlocalizedName("AluminumPowder").setTextureName("scirev:alpowder");
	public static Item meltiron = new Item().setUnlocalizedName("MeltIron").setTextureName("scirev:meltiron");

	public static Item co = new Item().setUnlocalizedName("CarbonMonoxide").setTextureName("scirev:gasbottle_co");
	public static Item gb = new Item().setUnlocalizedName("Beaker").setTextureName("scirev:beaker");
	public static Item wb = new Item().setUnlocalizedName("Beaker-H2O").setTextureName("scirev:beaker-h2o");

	public static Item bt = new Item().setUnlocalizedName("Battery").setTextureName("scirev:battery");

	public static Item copperplate = new Item().setUnlocalizedName("CopperPlate").setTextureName("scirev:copper_plate");
	public static Item alplate = new Item().setUnlocalizedName("AluminumPlate").setTextureName("scirev:aluminum_plate");
	public static Item ironplate = new Item().setUnlocalizedName("IronPlate").setTextureName("scirev:iron_plate");
	public static Item steelplate = new Item().setUnlocalizedName("SteelPlate").setTextureName("scirev:steel_plate");
	public static Item alshell = new Item().setUnlocalizedName("AluminumShell").setTextureName("scirev:aluminum_shell");
	public static Item ironshell = new Item().setUnlocalizedName("IronShell").setTextureName("scirev:iron_shell");

	public static Item coil = new Item().setUnlocalizedName("Coil").setTextureName("scirev:coil");
	public static Item motor = new Item().setUnlocalizedName("Motor").setTextureName("scirev:motor");

	public static Item coppercable = new CableItem(cable, 2).setUnlocalizedName("CopperCable")
	        .setTextureName("scirev:copper_cable");
	public static Item alcable = new CableItem(cable, 1).setUnlocalizedName("AluminumCable")
	        .setTextureName("scirev:al_cable");

	public static Item rawrubber = new Item().setUnlocalizedName("RawRubber").setTextureName("scirev:rawrubber");
	public static Item rubber = new Item().setUnlocalizedName("Rubber").setTextureName("scirev:rubber");

	public static CraftiveItem cp = (CraftiveItem) new CraftiveItem().setUnlocalizedName("CuttingPincer")
	        .setTextureName("scirev:cutting_pincer").setMaxDamage(80).setMaxStackSize(1);
	public static CraftiveItem hammer = (CraftiveItem) new CraftiveItem().setUnlocalizedName("Hammer")
	        .setTextureName("scirev:hammer").setMaxDamage(120).setMaxStackSize(1);

	//Replace Item
	public static Item itembowl = new BowlItem(bowl).setUnlocalizedName("bowl").setTextureName("minecraft:bowl")
	        .setCreativeTab(CreativeTabs.tabMaterials);

	public static CreativeTabs scirevCTab = new CreativeTabs("SciRevolution") {
		@Override
		public Item getTabIconItem() {
			return new ItemStack(ore_copperblock).getItem();

		}

	};

	@SidedProxy(clientSide = "com.scirev.client.ClientProxy", serverSide = "com.scirev.server.ServerProxy")
	public static IGuiHandler proxy;

	@Instance(MODID)
	public static SciRevolution instance;

	@EventHandler
	public void preLoad(FMLInitializationEvent event) {

		//Sets
		ore_copperblock.setCreativeTab(scirevCTab);
		ore_alblock.setCreativeTab(scirevCTab);
		ore_magblock.setCreativeTab(scirevCTab);
		se.setCreativeTab(scirevCTab);
		gen.setCreativeTab(scirevCTab);
		mac.setCreativeTab(scirevCTab);
		ex.setCreativeTab(scirevCTab);
		ef.setCreativeTab(scirevCTab);
		bf.setCreativeTab(scirevCTab);
		lbs.setCreativeTab(scirevCTab);
		lmp.setCreativeTab(scirevCTab);
		lps.setCreativeTab(scirevCTab);

		alingot.setCreativeTab(scirevCTab);
		copperingot.setCreativeTab(scirevCTab);
		magnet.setCreativeTab(scirevCTab);
		steel_ingot.setCreativeTab(scirevCTab);

		copperplate.setCreativeTab(scirevCTab);
		alplate.setCreativeTab(scirevCTab);
		ironplate.setCreativeTab(scirevCTab);
		alplate.setCreativeTab(scirevCTab);
		ironplate.setCreativeTab(scirevCTab);
		steelplate.setCreativeTab(scirevCTab);
		alshell.setCreativeTab(scirevCTab);
		ironshell.setCreativeTab(scirevCTab);

		coppercable.setCreativeTab(scirevCTab);
		alcable.setCreativeTab(scirevCTab);

		coil.setCreativeTab(scirevCTab);
		motor.setCreativeTab(scirevCTab);
		bt.setCreativeTab(scirevCTab);

		cp.setCreativeTab(scirevCTab);
		hammer.setCreativeTab(scirevCTab);

		ironpowder.setCreativeTab(scirevCTab);
		copperpowder.setCreativeTab(scirevCTab);
		alpowder.setCreativeTab(scirevCTab);
		meltiron.setCreativeTab(scirevCTab);

		co.setCreativeTab(scirevCTab);
		gb.setCreativeTab(scirevCTab);
		wb.setCreativeTab(scirevCTab);
		rawrubber.setCreativeTab(scirevCTab);
		rubber.setCreativeTab(scirevCTab);

		insulationstone.setCreativeTab(scirevCTab);
		alshellblock.setCreativeTab(scirevCTab);
		ironshellblock.setCreativeTab(scirevCTab);

		generallog.setCreativeTab(scirevCTab);
		generalleave.setCreativeTab(scirevCTab);
		generalsapling.setCreativeTab(scirevCTab);
		
		woodenpipe.setCreativeTab(scirevCTab);

		//Registry
		GameRegistry.registerBlock(ore_copperblock, ore_copperblock.getUnlocalizedName());
		GameRegistry.registerBlock(ore_alblock, ore_alblock.getUnlocalizedName());
		GameRegistry.registerBlock(ore_magblock, ore_magblock.getUnlocalizedName());
		GameRegistry.registerBlock(se, se.getUnlocalizedName());
		GameRegistry.registerBlock(gen, gen.getUnlocalizedName());
		GameRegistry.registerBlock(lit_gen, lit_gen.getUnlocalizedName());
		GameRegistry.registerBlock(mac, mac.getUnlocalizedName());
		GameRegistry.registerBlock(lit_mac, lit_mac.getUnlocalizedName());
		GameRegistry.registerBlock(ex, ex.getUnlocalizedName());
		GameRegistry.registerBlock(lit_ex, lit_ex.getUnlocalizedName());
		GameRegistry.registerBlock(ef, ef.getUnlocalizedName());
		GameRegistry.registerBlock(lit_ef, lit_ef.getUnlocalizedName());
		GameRegistry.registerBlock(bf, bf.getUnlocalizedName());
		GameRegistry.registerBlock(lit_bf, lit_bf.getUnlocalizedName());
		GameRegistry.registerBlock(lbs, lbs.getUnlocalizedName());
		GameRegistry.registerBlock(lmp, lmp.getUnlocalizedName());
		GameRegistry.registerBlock(lps, lps.getUnlocalizedName());
		GameRegistry.registerBlock(cable, cable.getUnlocalizedName());
		GameRegistry.registerBlock(insulationstone, insulationstone.getUnlocalizedName());
		GameRegistry.registerBlock(alshellblock, alshellblock.getUnlocalizedName());
		GameRegistry.registerBlock(ironshellblock, ironshellblock.getUnlocalizedName());

		GameRegistry.registerBlock(generallog, LogItems.class, generallog.getUnlocalizedName());
		GameRegistry.registerBlock(generalleave, LeafItems.class, generalleave.getUnlocalizedName());
		GameRegistry.registerBlock(generalsapling, SaplingItems.class, generalsapling.getUnlocalizedName());

		GameRegistry.registerBlock(cutrubberlog, cutrubberlog.getUnlocalizedName());
		GameRegistry.registerBlock(norubberlog, norubberlog.getUnlocalizedName());
		GameRegistry.registerBlock(woodenpipe, woodenpipe.getUnlocalizedName());
		GameRegistry.registerBlock(bowl, bowl.getUnlocalizedName());

		GameRegistry.registerItem(alingot, alingot.getUnlocalizedName());
		GameRegistry.registerItem(copperingot, copperingot.getUnlocalizedName());
		GameRegistry.registerItem(magnet, magnet.getUnlocalizedName());
		GameRegistry.registerItem(steel_ingot, steel_ingot.getUnlocalizedName());

		GameRegistry.registerItem(ironpowder, ironpowder.getUnlocalizedName());
		GameRegistry.registerItem(copperpowder, copperpowder.getUnlocalizedName());
		GameRegistry.registerItem(alpowder, alpowder.getUnlocalizedName());
		GameRegistry.registerItem(meltiron, meltiron.getUnlocalizedName());

		GameRegistry.registerItem(co, co.getUnlocalizedName());
		GameRegistry.registerItem(gb, gb.getUnlocalizedName());
		GameRegistry.registerItem(wb, wb.getUnlocalizedName());
		GameRegistry.registerItem(rawrubber, rawrubber.getUnlocalizedName());
		GameRegistry.registerItem(rubber, rubber.getUnlocalizedName());

		GameRegistry.registerItem(copperplate, copperplate.getUnlocalizedName());
		GameRegistry.registerItem(alplate, alplate.getUnlocalizedName());
		GameRegistry.registerItem(ironplate, ironplate.getUnlocalizedName());
		GameRegistry.registerItem(steelplate, steelplate.getUnlocalizedName());
		GameRegistry.registerItem(alshell, alshell.getUnlocalizedName());
		GameRegistry.registerItem(ironshell, ironshell.getUnlocalizedName());

		GameRegistry.registerItem(coppercable, coppercable.getUnlocalizedName());
		GameRegistry.registerItem(alcable, alcable.getUnlocalizedName());

		GameRegistry.registerItem(coil, coil.getUnlocalizedName());
		GameRegistry.registerItem(motor, motor.getUnlocalizedName());
		GameRegistry.registerItem(bt, bt.getUnlocalizedName());

		GameRegistry.registerItem(cp, cp.getUnlocalizedName());
		GameRegistry.registerItem(hammer, hammer.getUnlocalizedName());

		//Replace
		try {
			GameRegistry.addSubstitutionAlias("minecraft:bowl", Type.ITEM, itembowl);
		} catch (ExistingSubstitutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GameRegistry.addSmelting(ore_copperblock, new ItemStack(copperingot), 0.6f);
		GameRegistry.addSmelting(copperpowder, new ItemStack(copperingot), 0.6f);
		GameRegistry.addSmelting(ore_magblock, new ItemStack(magnet), 0.6f);

		GameRegistry.addSmelting(Items.coal, new ItemStack(co, 4), 0.3f);
		GameRegistry.addSmelting(rawrubber, new ItemStack(rubber, 1), 0.3f);

		GameRegistry.addShapedRecipe(new ItemStack(cp),
		        new Object[] { "1 1", " 1 ", "2 2", '1', Items.iron_ingot, '2', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(hammer),
		        new Object[] { "111", " 1 ", " 2 ", '1', Items.iron_ingot, '2', Items.stick });

		GameRegistry.addShapedRecipe(new ItemStack(coil),
		        new Object[] { "111", "121", "111", '2', Items.iron_ingot, '1', coppercable });
		GameRegistry.addShapedRecipe(new ItemStack(motor), new Object[] { "121", "353", "424", '1', alplate, '2',
		        magnet, '3', Items.iron_ingot, '4', coppercable, '5', coil });

		GameRegistry.addShapedRecipe(new ItemStack(insulationstone),
		        new Object[] { "111", "1 1", "1 1", '1', Blocks.cobblestone });
		GameRegistry.addShapedRecipe(new ItemStack(alshellblock), new Object[] { "111", "1 1", "111", '1', alshell });
		GameRegistry.addShapedRecipe(new ItemStack(ironshellblock),
		        new Object[] { "111", "1 1", "111", '1', ironshell });
		GameRegistry.addShapedRecipe(new ItemStack(bf), new Object[] { "121", "134", "121", '1', Blocks.cobblestone,
		        '2', insulationstone, '3', copperingot, '4', gb });
		GameRegistry.addShapedRecipe(new ItemStack(gen), new Object[] { "124", "131", "151", '1', ironplate, '2', motor,
		        '3', wb, '4', bt, '5', Blocks.furnace });
		GameRegistry.addShapedRecipe(new ItemStack(mac),
		        new Object[] { "111", "232", "111", '1', steelplate, '2', motor, '3', Items.flint });
		GameRegistry.addShapedRecipe(new ItemStack(ex),
		        new Object[] { "121", "3 3", "421", '1', steelplate, '2', wb, '3', Blocks.stone, '4', motor });

		GameRegistry.addShapedRecipe(new ItemStack(bt),
		        new Object[] { " 1 ", "232", " 3 ", '1', Items.coal, '2', alplate, '3', Items.redstone });
		GameRegistry.addShapedRecipe(new ItemStack(woodenpipe),
		        new Object[] { "   ", "1 1", "111", '1', Blocks.planks });

		RecipeSorter.register("scirev:shaped", AdvRecipe.class, RecipeSorter.Category.SHAPED,
		        "after:minecraft:shapeless");
		RecipeSorter.register("scirev:shapeless", AdvShapelessRecipe.class, RecipeSorter.Category.SHAPELESS,
		        "after:scirev:shaped");

		GameRegistry.addShapelessRecipe(new ItemStack(copperplate), copperingot,
		        new ItemStack(hammer, 1, OreDictionary.WILDCARD_VALUE));
		GameRegistry.addShapelessRecipe(new ItemStack(alplate), alingot,
		        new ItemStack(hammer, 1, OreDictionary.WILDCARD_VALUE));
		GameRegistry.addShapelessRecipe(new ItemStack(ironplate), Items.iron_ingot,
		        new ItemStack(hammer, 1, OreDictionary.WILDCARD_VALUE));

		GameRegistry.addShapelessRecipe(new ItemStack(gb), Blocks.glass, Blocks.glass);
		GameRegistry.addShapelessRecipe(new ItemStack(wb), gb, Items.water_bucket);

		GameRegistry.addShapelessRecipe(new ItemStack(coppercable, 3), copperplate,
		        new ItemStack(cp, 1, OreDictionary.WILDCARD_VALUE));
		GameRegistry.addShapelessRecipe(new ItemStack(alcable, 3), alplate,
		        new ItemStack(cp, 1, OreDictionary.WILDCARD_VALUE));

		GameRegistry.registerTileEntity(GeneratorEntity.class, "GeneratorTileEntity");
		GameRegistry.registerTileEntity(MaceratorTileEntity.class, "MaceratorTileEntity");
		GameRegistry.registerTileEntity(ExtrusionerTileEntity.class, "ExtrusionerTileEntity");
		GameRegistry.registerTileEntity(BlastFurnaceEntity.class, "BlastFurnaceTileEntity");
		GameRegistry.registerTileEntity(ElectroFurnaceEntity.class, "ElectroFurnaceTileEntity");
		GameRegistry.registerTileEntity(CableEntity.class, "CableTileEntity");
		GameRegistry.registerTileEntity(LatheBottomShellEntity.class, "LatheBottomShellEntity");
		GameRegistry.registerTileEntity(LatheManipulatePanelEntity.class, "LatheManipulatePanelEntity");
		GameRegistry.registerTileEntity(LathePowerSourceEntity.class, "LathePowerSourceEntity");
		GameRegistry.registerTileEntity(WoodenPipeEntity.class, "WoodenPipeEntity");
		GameRegistry.registerTileEntity(BowlEntity.class, "BowlEntity");
		GameRegistry.registerTileEntity(SteamEngineEntity.class, "SteamEngineEntity");

		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);

		GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
		GameRegistry.registerWorldGenerator(new TreeGenerator(), 1);

		ClientRegistry.bindTileEntitySpecialRenderer(CableEntity.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(LatheBottomShellEntity.class, new RenderLBS());
		ClientRegistry.bindTileEntitySpecialRenderer(LatheManipulatePanelEntity.class, new RenderLMP());
		ClientRegistry.bindTileEntitySpecialRenderer(LathePowerSourceEntity.class, new RenderLPS());
		ClientRegistry.bindTileEntitySpecialRenderer(WoodenPipeEntity.class, new RenderPipe());
		ClientRegistry.bindTileEntitySpecialRenderer(BowlEntity.class, new RenderBowl());
		ClientRegistry.bindTileEntitySpecialRenderer(SteamEngineEntity.class, new RenderSE());

		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.woodenpipe),
		        new GenericItemRenderer(
		                new ResourceLocation("scirev:textures/blocks/woodenpipe.png"),
		                new ModelPipe()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.lmp),
		        new GenericItemRenderer(new ResourceLocation("scirev:textures/blocks/lmp.png"),
		                new ModelLatheManipulatePanel()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.lps),
		        new GenericItemRenderer(new ResourceLocation("scirev:textures/blocks/lps.png"),
		                new ModelLathePowerSource()));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(SciRevolution.lbs),
		        new GenericItemRenderer(new ResourceLocation("scirev:textures/blocks/lbs.png"),
		                new ModelLatheBottomShell()));

		//Removing Original
		removeSmelt(Blocks.iron_ore);

		removeCrafting(Blocks.golden_rail);

		MaceratorCraftingRecipe.registerRecipe();
		BlastFurnaceRecipe.registerRecipe();
		ExtrusionerCraftingRecipe.registerRecipe();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	public static void removeSmelt(Item target) {
		ArrayList<Object> needRemove = new ArrayList<Object>();
		for (Object object : FurnaceRecipes.smelting().getSmeltingList().keySet()) {
			Item oItem = ((ItemStack) object).getItem();
			if (oItem.equals(target)) {
				needRemove.add(object);
			}
		}

		for (Object object : needRemove.toArray(new Object[0])) {
			FurnaceRecipes.smelting().getSmeltingList().remove(object);
		}

	}

	public static void removeCrafting(Block object) {
		removeCrafting(Item.getItemFromBlock(object));
	}

	public static void removeCrafting(Item target) {
		ArrayList<Object> needRemove = new ArrayList<Object>();
		@SuppressWarnings({ "rawtypes" })
		List list = CraftingManager.getInstance().getRecipeList();
		for (int i = 0; i < list.size(); i++) {
			try {
				Item oItem = ((IRecipe) list.get(i)).getRecipeOutput().getItem();
				if (oItem.equals(target)) {
					needRemove.add(list.get(i));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		for (Object object : needRemove.toArray(new Object[0])) {
			CraftingManager.getInstance().getRecipeList().remove(object);
		}

	}

	public static void removeSmelt(Block object) {
		removeSmelt(Item.getItemFromBlock(object));
	}
}
