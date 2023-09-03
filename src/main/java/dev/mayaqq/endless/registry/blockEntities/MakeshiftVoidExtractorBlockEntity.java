package dev.mayaqq.endless.registry.blockEntities;

import dev.mayaqq.endless.registry.EndlessBlockEntities;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

import static dev.mayaqq.endless.Endless.id;

public class MakeshiftVoidExtractorBlockEntity extends LootableContainerBlockEntity implements ExtendedScreenHandlerFactory {

    public static final int INVENTORY_SIZE = 2;
    public static final int INPUT_SLOT_ID = 0;
    public static final int OUTPUT_SLOT_ID = 1;
    public DefaultedList<ItemStack> inventory;
    protected boolean paused;
    protected boolean endergyDirty;
    private UUID ownerUUID;

    public MakeshiftVoidExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(EndlessBlockEntities.MAKESHIFT_VOID_EXTRACTOR_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(INVENTORY_SIZE, ItemStack.EMPTY);
    }

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return (8 * FluidConstants.BUCKET) / 81;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if (!world.isClient) {
                var buf = PacketByteBufs.create();
                PlayerLookup.tracking(MakeshiftVoidExtractorBlockEntity.this).forEach(player -> {
                    ServerPlayNetworking.send(player, id("extractorfluid"), buf);
                });
            }
        }
    };

    public static void tick(World world, BlockPos pos, BlockState state, MakeshiftVoidExtractorBlockEntity be) {
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        fluidStorage.variant = FluidVariant.fromNbt(nbt.getCompound("fluidVariant"));
        fluidStorage.amount = nbt.getLong("amount");
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inventory);
        }
        if (nbt.contains("OwnerUUID")) {
            this.ownerUUID = nbt.getUuid("OwnerUUID");
        } else {
            this.ownerUUID = null;
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.put("fluidVariant", fluidStorage.variant.toNbt());
        nbt.putLong("amount", fluidStorage.amount);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory);
        }
        if (this.ownerUUID != null) {
            nbt.putUuid("OwnerUUID", this.ownerUUID);
        }
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("container.endless.makeshift_void_extractor");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        //return new MakeshiftVoidExtractorScreenHandler(syncId, playerInventory, this.pos);
        return null;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
        this.paused = false;
        updateInClientWorld();
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack itemStack = super.removeStack(slot, amount);
        this.paused = false;
        updateInClientWorld();
        return itemStack;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
        this.paused = false;
        updateInClientWorld();
    }

    @Override
    public int size() {
        return INVENTORY_SIZE;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbtCompound = new NbtCompound();
        this.writeNbt(nbtCompound);
        return nbtCompound;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public void updateInClientWorld() {
        world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), Block.NO_REDRAW);
    }
}