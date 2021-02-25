package com.devsdg.digipos.GestionStock.Models;

import com.devsdg.digipos.GestionProduits.Models.Produit;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Stock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idstock;
    private int totalStock;
    private int reservedStock;
    private int shoppingStock;
    private int stockAlarmLimit;
    @JsonFormat(timezone = "GMT+01:00")
    private Date dateManufacture;
    @JsonFormat(timezone = "GMT+01:00")
    private Date dateExpiration;
    private int dernierAjout;

    @JsonIgnore
    @OneToOne(mappedBy = "stock")
    private Produit produit;

    public Stock(String stockName, int actualStock, int reservedStock, int shoppingStock, int stockAlarmLimit, Produit produit) {
        this.totalStock = actualStock;
        this.reservedStock = reservedStock;
        this.shoppingStock = shoppingStock;
        this.stockAlarmLimit = stockAlarmLimit;
        this.produit = produit;
    }

    public Stock() {
    }

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

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
