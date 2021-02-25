package com.devsdg.digipos.GestionStock.DTO;

import com.devsdg.digipos.GestionProduits.Models.Produit;
import java.util.Date;

public class StockDTO {
    private Long idstock;
    private int totalStock;
    private int reservedStock;
    private int shoppingStock;
    private int stockAlarmLimit;
    private Date dateManufacture;
    private Date dateExpiration;
    private int dernierAjout;
    private Long idProduit;
    private int quantite;
    private Produit produit;

    public Long getIdstock() {
        return idstock;
    }

    public void setIdstock(Long idstock) {
        this.idstock = idstock;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public int getReservedStock() {
        return reservedStock;
    }

    public void setReservedStock(int reservedStock) {
        this.reservedStock = reservedStock;
    }

    public int getShoppingStock() {
        return shoppingStock;
    }

    public void setShoppingStock(int shoppingStock) {
        this.shoppingStock = shoppingStock;
    }

    public int getStockAlarmLimit() {
        return stockAlarmLimit;
    }

    public void setStockAlarmLimit(int stockAlarmLimit) {
        this.stockAlarmLimit = stockAlarmLimit;
    }

    public Date getDateManufacture() {
        return dateManufacture;
    }

    public void setDateManufacture(Date dateManufacture) {
        this.dateManufacture = dateManufacture;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public int getDernierAjout() {
        return dernierAjout;
    }

    public void setDernierAjout(int dernierAjout) {
        this.dernierAjout = dernierAjout;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
