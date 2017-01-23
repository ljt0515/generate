/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ljt.generate;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ljt
 */
public class GenerateJFrame extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form GenerateJFrame
	 */
	public GenerateJFrame() {
		initComponents();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jFileChooser = new javax.swing.JFileChooser();
		jFileChooser.setDialogTitle("请选择生成目录");
		jFileChooser.setFileSelectionMode(1);// 设定只能选择到文件夹
		generateJButton = new javax.swing.JButton();
		cancelJButton = new javax.swing.JButton();
		databaseJPanel = new javax.swing.JPanel();
		databaseJScrollPane = new javax.swing.JScrollPane();
		databaseJList = new javax.swing.JList<>();
		tablesJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		tablesJScrollPane = new JScrollPane(tablesJPanel);
		tablesJScrollPane.setBorder(BorderFactory.createTitledBorder("数据表"));
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		fileJButton = new javax.swing.JButton();
		fileJTextField = new javax.swing.JTextField();
		daoJTextField = new javax.swing.JTextField();
		daoJLabel = new javax.swing.JLabel();
		serviceJLabel = new javax.swing.JLabel();
		serviceJTextField = new javax.swing.JTextField();
		entityJLabel = new javax.swing.JLabel();
		entityJTextField = new javax.swing.JTextField();
		fileJLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		generateJButton.setText("生成");
		final Set<JCheckBox> set = new HashSet<JCheckBox>();
		generateJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Set<String> tables = new HashSet<String>();
				for (JCheckBox jCheckBox : set) {
					if (jCheckBox.isSelected()) {
						tables.add(jCheckBox.getText());
					}
				}
				if (tables.isEmpty()) {
					JOptionPane.showMessageDialog(null, "请选择要生成的表名", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Config.file_path = fileJTextField.getText();
					Config.service_package = serviceJTextField.getText();
					Config.bean_package = entityJTextField.getText();
					Config.mapper_package = daoJTextField.getText();
					new EntityUtil().generate(tables);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				int showConfirmDialog = JOptionPane.showConfirmDialog(null, "生成成功,是否打开文件夹", "提示",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				System.out.println(showConfirmDialog);
				if (showConfirmDialog == 0) {
					try {
						Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start", "", fileJTextField.getText()});
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		cancelJButton.setText("取消");
		cancelJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		databaseJList.setModel(new javax.swing.AbstractListModel<String>() {
			private static final long serialVersionUID = 1L;
			String[] strings = EntityUtil.getDatabases().split(",");

			public int getSize() {
				return strings.length;
			}

			public String getElementAt(int i) {
				return strings[i];
			}
		});
		databaseJList.addListSelectionListener(new ListSelectionListener() {
			int i = 0;
			int count = 0;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				i++;
				count = 0;
				if (i > 1) {
					try {
						Config.database = databaseJList.getSelectedValue();
						EntityUtil.init();
						tablesJPanel.removeAll();
						tablesJPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
						final JCheckBox jCheckBoxAll = new JCheckBox("全选");
						jCheckBoxAll.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								for (JCheckBox jCheckBox : set) {
									jCheckBox.setSelected(jCheckBoxAll.isSelected());
								}
							}
						});
						tablesJPanel.add(jCheckBoxAll);
						List<String> tables = EntityUtil.getTables();
						for (String string : tables) {
							count++;
							JCheckBox jCheckBox = new JCheckBox(string);
							set.add(jCheckBox);
							tablesJPanel.add(jCheckBox);
							// validate();
							int column = 320 / 200;
							double row = count / column;
							int d = (int) Math.ceil(row);
							tablesJPanel.setPreferredSize(new Dimension(520, (int) (d * 11) - 35));//
							tablesJPanel.updateUI();
							tablesJScrollPane.updateUI();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					i = 0;
				}
			}
		});
		databaseJScrollPane.setViewportView(databaseJList);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(databaseJPanel);
		databaseJScrollPane.setBorder(BorderFactory.createTitledBorder("数据库"));
		databaseJPanel.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout
						.createSequentialGroup().addComponent(databaseJScrollPane,
								javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(
								databaseJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 376,
								javax.swing.GroupLayout.PREFERRED_SIZE)));

		fileJButton.setText("...");
		fileJButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				int state = jFileChooser.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
				if (state == 1) {
					return;
				} else {
					File f = jFileChooser.getSelectedFile();// f为选择到的目录
					fileJTextField.setText(f.getAbsolutePath());
				}
			}
		});

		fileJTextField.setEditable(false);
		fileJTextField.setToolTipText("文件路径");
		daoJTextField.setText("com.examples.dao");
		serviceJTextField.setText("com.examples.service");
		entityJTextField.setText("com.examples.entity");
		daoJLabel.setText("Dao包名");

		serviceJLabel.setText("service包名");

		entityJLabel.setText("entity包名");

		fileJLabel.setText("生成路径");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(168, 168, 168).addComponent(generateJButton)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251,
										Short.MAX_VALUE)
								.addComponent(cancelJButton).addGap(195, 195, 195))
						.addGroup(layout.createSequentialGroup()
								.addComponent(databaseJPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(tablesJScrollPane).addContainerGap())
						.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(fileJLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(fileJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 98,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(fileJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(daoJLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(daoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(serviceJLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(serviceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(entityJLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(entityJTextField).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(fileJButton)
								.addComponent(fileJTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(daoJTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(daoJLabel).addComponent(serviceJLabel)
								.addComponent(serviceJTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(entityJLabel)
								.addComponent(entityJTextField, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(fileJLabel))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(databaseJPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tablesJScrollPane))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(generateJButton).addComponent(cancelJButton))
						.addGap(38, 38, 38)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton generateJButton;
	private javax.swing.JButton cancelJButton;
	private javax.swing.JButton fileJButton;
	private javax.swing.JLabel daoJLabel;
	private javax.swing.JLabel serviceJLabel;
	private javax.swing.JLabel entityJLabel;
	private javax.swing.JLabel fileJLabel;
	private javax.swing.JList<String> databaseJList;
	private javax.swing.JPanel databaseJPanel;
	private javax.swing.JPanel tablesJPanel;
	private javax.swing.JScrollPane tablesJScrollPane;
	private javax.swing.JScrollPane databaseJScrollPane;
	private javax.swing.JTextField fileJTextField;
	private javax.swing.JTextField daoJTextField;
	private javax.swing.JTextField serviceJTextField;
	private javax.swing.JTextField entityJTextField;
	private javax.swing.JFileChooser jFileChooser;
	// End of variables declaration//GEN-END:variables
}