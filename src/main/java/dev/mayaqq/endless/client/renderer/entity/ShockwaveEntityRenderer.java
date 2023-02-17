package dev.mayaqq.endless.client.renderer.entity;

import dev.mayaqq.endless.entities.ShockwaveEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;

public class ShockwaveEntityRenderer extends EntityRenderer<ShockwaveEntity> {
    private static final RenderLayer LAYER = RenderLayer.of("cylinder", VertexFormats.POSITION_COLOR, VertexFormat.DrawMode.QUADS, 256, RenderLayer.MultiPhaseParameters.builder().shader(new Shader() {
        @Override
        public void apply(ShaderEffect shaderEffect) {
            GlStateManager.disableTexture();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        }
    }).cull(DISABLE_CULLING).build(false));

    public ShockwaveEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public void render(ShockwaveEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(LAYER);
        MatrixStack.Entry entry = matrices.peek();

        float prevX = MathHelper.sin(0F) * entity.getRadius();
        float prevY = MathHelper.cos(0F) * entity.getRadius();

        for (int i = 1; i <= 16; i++) {
            float currX = MathHelper.sin((float) (i * Math.PI / 8)) * entity.getRadius();
            float currY = MathHelper.cos((float) (i * Math.PI / 8)) * entity.getRadius();

            vertexConsumer.vertex(entry.getModel(), prevX, prevY, 0F).color(entity.getRed(), entity.getGreen(), entity.getBlue(), entity.getAlpha(tickDelta)).next();
            vertexConsumer.vertex(entry.getModel(), prevX, prevY, entity.getHeight()).color(entity.getRed(), entity.getGreen(), entity.getBlue(), entity.getAlpha(tickDelta)).next();
            vertexConsumer.vertex(entry.getModel(), currX, currY, entity.getHeight()).color(entity.getRed(), entity.getGreen(), entity.getBlue(), entity.getAlpha(tickDelta)).next();
            vertexConsumer.vertex(entry.getModel(), currX, currY, 0F).color(entity.getRed(), entity.getGreen(), entity.getBlue(), entity.getAlpha(tickDelta)).next();

            prevX = currX;
            prevY = currY;
        }

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(ShockwaveEntity entity) {
        return null;
    }
}