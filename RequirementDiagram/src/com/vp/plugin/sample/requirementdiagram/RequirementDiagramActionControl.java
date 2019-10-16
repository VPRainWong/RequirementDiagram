package com.vp.plugin.sample.requirementdiagram;

import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IDiagramTypeConstants;
import com.vp.plugin.diagram.IRequirementDiagramUIModel;
import com.vp.plugin.diagram.shape.IRequirementUIModel;
import com.vp.plugin.model.IRequirement;
import com.vp.plugin.model.IStereotype;
import com.vp.plugin.model.ITaggedValue;
import com.vp.plugin.model.ITaggedValueDefinition;
import com.vp.plugin.model.ITaggedValueDefinitionContainer;
import com.vp.plugin.model.factory.IModelElementFactory;

public class RequirementDiagramActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// Create Requirement Diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IRequirementDiagramUIModel reqDiagram = (IRequirementDiagramUIModel) diagramManager.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_REQUIREMENT_DIAGRAM);
		reqDiagram.setName("Top-Level User Requirements");
		
		IModelElementFactory factory = IModelElementFactory.instance();
		
		// Create functionalRequirement stereotype
		IStereotype typeFunctionalRequirements = factory.createStereotype();
		typeFunctionalRequirements.setName("functionalRequirement");
		// Specify the stereotype is for IRequirement model type
		typeFunctionalRequirements.setBaseType(IModelElementFactory.MODEL_TYPE_REQUIREMENT);
		// Define id, source & text as text-type tagged value
		ITaggedValueDefinitionContainer tagsFunctionalRequirements = factory.createTaggedValueDefinitionContainer();
		typeFunctionalRequirements.setTaggedValueDefinitions(tagsFunctionalRequirements);
		ITaggedValueDefinition tagFunctionalRequirementsId = tagsFunctionalRequirements.createTaggedValueDefinition();
		tagFunctionalRequirementsId.setName("id");
		tagFunctionalRequirementsId.setType(ITaggedValueDefinition.TYPE_TEXT);
		ITaggedValueDefinition tagFunctionalRequirementsSource = tagsFunctionalRequirements.createTaggedValueDefinition();
		tagFunctionalRequirementsSource.setName("source");
		tagFunctionalRequirementsSource.setType(ITaggedValueDefinition.TYPE_TEXT);		
		ITaggedValueDefinition tagFunctionalRequirementsText = tagsFunctionalRequirements.createTaggedValueDefinition();
		tagFunctionalRequirementsText.setName("text");
		tagFunctionalRequirementsText.setType(ITaggedValueDefinition.TYPE_TEXT);
		// Define verifyMehtod & risk as enum tagged value
		ITaggedValueDefinition tagFunctionalRequirementsVerificationMethod = tagsFunctionalRequirements.createTaggedValueDefinition();
		tagFunctionalRequirementsVerificationMethod.setName("verifyMethod");
		tagFunctionalRequirementsVerificationMethod.setType(ITaggedValueDefinition.TYPE_ENUMERATION);
		tagFunctionalRequirementsVerificationMethod.setEnumerationValues(new String[] {"Analysis", "Demonstration", "Inspection", "Test"});
		ITaggedValueDefinition tagFunctionalRequirementsRisk = tagsFunctionalRequirements.createTaggedValueDefinition();
		tagFunctionalRequirementsRisk.setName("risk");
		tagFunctionalRequirementsRisk.setType(ITaggedValueDefinition.TYPE_ENUMERATION);
		tagFunctionalRequirementsRisk.setEnumerationValues(new String[] {"High", "Medium", "Low"});
		
		// Create performanceRequirement stereotype
		IStereotype typePerformanceRequirements = factory.createStereotype();
		typePerformanceRequirements.setName("performanceRequirement");
		typePerformanceRequirements.setBaseType(IModelElementFactory.MODEL_TYPE_REQUIREMENT);
		ITaggedValueDefinitionContainer tagsPerformanceRequirements = factory.createTaggedValueDefinitionContainer();
		typePerformanceRequirements.setTaggedValueDefinitions(tagsPerformanceRequirements);
		ITaggedValueDefinition tagPerformanceRequirementsId = tagsPerformanceRequirements.createTaggedValueDefinition();
		tagPerformanceRequirementsId.setName("id");
		tagPerformanceRequirementsId.setType(ITaggedValueDefinition.TYPE_TEXT);
		ITaggedValueDefinition tagPerformanceRequirementsSource = tagsPerformanceRequirements.createTaggedValueDefinition();
		tagPerformanceRequirementsSource.setName("source");
		tagPerformanceRequirementsSource.setType(ITaggedValueDefinition.TYPE_TEXT);		
		ITaggedValueDefinition tagPerformanceRequirementsText = tagsPerformanceRequirements.createTaggedValueDefinition();
		tagPerformanceRequirementsText.setName("text");
		tagPerformanceRequirementsText.setType(ITaggedValueDefinition.TYPE_TEXT);
		ITaggedValueDefinition tagPerformanceRequirementsVerificationMethod = tagsPerformanceRequirements.createTaggedValueDefinition();
		tagPerformanceRequirementsVerificationMethod.setName("verifyMethod");
		tagPerformanceRequirementsVerificationMethod.setType(ITaggedValueDefinition.TYPE_ENUMERATION);
		tagPerformanceRequirementsVerificationMethod.setEnumerationValues(new String[] {"Analysis", "Demonstration", "Inspection", "Test"});
		ITaggedValueDefinition tagPerformanceRequirementsRisk = tagsPerformanceRequirements.createTaggedValueDefinition();
		tagPerformanceRequirementsRisk.setName("risk");
		tagPerformanceRequirementsRisk.setType(ITaggedValueDefinition.TYPE_ENUMERATION);
		tagPerformanceRequirementsRisk.setEnumerationValues(new String[] {"High", "Medium", "Low"});
		
		// Create root requirement model and specify its custom ID value
		IRequirement reqHybridSUV = factory.createRequirement();
		reqHybridSUV.setName("HybirdSUV");
		reqHybridSUV.setUserID("UR1");
		
		// Create Load requirement model
		IRequirement reqLoad = factory.createRequirement();
		reqLoad.setName("Load");
		// Remove its default requirement type and assign the functionalRequirement to it
		reqLoad.removeStereotype("requirement");
		reqLoad.addStereotype(typeFunctionalRequirements);
		// Specify the values for tagged values associated with functionalRequirement
		ITaggedValue[] reqLoadTags = reqLoad.getTaggedValues().toTaggedValueArray();
		for (ITaggedValue tag : reqLoadTags) {
			if (typeFunctionalRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("id").equals(tag.getTagDefinition())) {
				tag.setValue("UR1.1");
			} else if (typeFunctionalRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("source").equals(tag.getTagDefinition())) {
				tag.setValue("Marketing");
			} else if (typeFunctionalRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("text").equals(tag.getTagDefinition())) {
				tag.setValue("Load");
			} else if (typeFunctionalRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("verifyMethod").equals(tag.getTagDefinition())) {
				tag.setValue("Test");
			} else if (typeFunctionalRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("risk").equals(tag.getTagDefinition())) {
				tag.setValue("Low");
			}
		}
		
		IRequirement reqEcoFriendliness = factory.createRequirement();
		reqEcoFriendliness.setName("Eco-Friendliness");
		reqEcoFriendliness.removeStereotype("requirement");
		reqEcoFriendliness.addStereotype(typePerformanceRequirements);
		ITaggedValue[] reqEcoTags = reqEcoFriendliness.getTaggedValues().toTaggedValueArray();
		for (ITaggedValue tag : reqEcoTags) {
			if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("id").equals(tag.getTagDefinition())) {
				tag.setValue("UR1.2");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("source").equals(tag.getTagDefinition())) {
				tag.setValue("Marketing");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("text").equals(tag.getTagDefinition())) {
				tag.setValue("Eco-Friendliness");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("verifyMethod").equals(tag.getTagDefinition())) {
				tag.setValue("Analysis");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("risk").equals(tag.getTagDefinition())) {
				tag.setValue("High");
			}
		}
		
		IRequirement reqPerformance = factory.createRequirement();
		reqPerformance.setName("Performance");
		reqPerformance.removeStereotype("requirement");
		reqPerformance.addStereotype(typePerformanceRequirements);
		ITaggedValue[] reqPerformanceTags = reqPerformance.getTaggedValues().toTaggedValueArray();
		for (ITaggedValue tag : reqPerformanceTags) {
			if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("id").equals(tag.getTagDefinition())) {
				tag.setValue("UR1.3");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("source").equals(tag.getTagDefinition())) {
				tag.setValue("Marketing");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("text").equals(tag.getTagDefinition())) {
				tag.setValue("Performance");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("verifyMethod").equals(tag.getTagDefinition())) {
				tag.setValue("Test");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("risk").equals(tag.getTagDefinition())) {
				tag.setValue("Medium");
			}
		}		
		
		IRequirement reqErgonomics = factory.createRequirement();
		reqErgonomics.setName("Ergonomics");
		reqErgonomics.setUserID("UR1.4");
		
		IRequirement reqPassengers = factory.createRequirement();
		reqPassengers.setName("Passengers");
		reqPassengers.setUserID("UR1.1.1");
		
		IRequirement reqFuelCapacity = factory.createRequirement();
		reqFuelCapacity.setName("FuelCapacity");
		reqFuelCapacity.setUserID("UR1.1.2");
		
		IRequirement reqCargo = factory.createRequirement();
		reqCargo.setName("Cargo");
		reqCargo.setUserID("UR1.1.3");
		
		IRequirement reqEmissions = factory.createRequirement();
		reqEmissions.setName("Emissions");
		reqEmissions.removeStereotype("requirement");
		reqEmissions.addStereotype(typePerformanceRequirements);
		ITaggedValue[] reqEmissionTags = reqEmissions.getTaggedValues().toTaggedValueArray();
		for (ITaggedValue tag : reqEmissionTags) {
			if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("id").equals(tag.getTagDefinition())) {
				tag.setValue("UR1.2.1");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("source").equals(tag.getTagDefinition())) {
				tag.setValue("Marketing");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("text").equals(tag.getTagDefinition())) {
				tag.setValue("The car shall meet 2010 Kyoto According emissions standards.");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("verifyMethod").equals(tag.getTagDefinition())) {
				tag.setValue("Test");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("risk").equals(tag.getTagDefinition())) {
				tag.setValue("Medium");
			}
		}			
		
		IRequirement reqAcceleration = factory.createRequirement();
		reqAcceleration.setName("Acceleration");
		reqAcceleration.setUserID("UR1.3.2");
		
		IRequirement reqFuelEconomy = factory.createRequirement();
		reqFuelEconomy.setName("FuelEconomy");
		reqFuelEconomy.removeStereotype("requirement");
		reqFuelEconomy.addStereotype(typePerformanceRequirements);
		ITaggedValue[] reqFuelEnconomyTags = reqFuelEconomy.getTaggedValues().toTaggedValueArray();
		for (ITaggedValue tag : reqFuelEnconomyTags) {
			if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("id").equals(tag.getTagDefinition())) {
				tag.setValue("UR1.3.1");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("source").equals(tag.getTagDefinition())) {
				tag.setValue("Marketing");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("text").equals(tag.getTagDefinition())) {
				tag.setValue("Users shall obtain fuel economy better than that provided by 95% of cars built in 2004.");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("verifyMethod").equals(tag.getTagDefinition())) {
				tag.setValue("Test");
			} else if (typePerformanceRequirements.getTaggedValueDefinitions().getTaggedValueDefinitionByName("risk").equals(tag.getTagDefinition())) {
				tag.setValue("High");
			}
		}				
		
		IRequirement reqBraking = factory.createRequirement();
		reqBraking.setName("Braking");
		reqBraking.setUserID("1.3.3");
		
		IRequirement reqRange = factory.createRequirement();
		reqRange.setName("Range");
		reqRange.setUserID("1.3.4");
		
		IRequirement reqPower = factory.createRequirement();
		reqPower.setName("Power");
		reqPower.setUserID("1.3.5");
		
		
		// Add Load, EcoFriendliness, Performance & Ergonomics as the children of requirement HybridSUV
		reqHybridSUV.addChild(reqLoad);
		reqHybridSUV.addChild(reqEcoFriendliness);
		reqHybridSUV.addChild(reqPerformance);
		reqHybridSUV.addChild(reqErgonomics);
		
		// Add Passengers, FuelCapacity & Cargo as children of Load
		reqLoad.addChild(reqPassengers);
		reqLoad.addChild(reqFuelCapacity);
		reqLoad.addChild(reqCargo);
		
		// Add Emissions as child of EcoFriendliness		
		reqEcoFriendliness.addChild(reqEmissions);
		
		// Add Acceleration, FuelEconomy, Braking & Range as children of Performance
		reqPerformance.addChild(reqAcceleration);
		reqPerformance.addChild(reqFuelEconomy);
		reqPerformance.addChild(reqBraking);
		reqPerformance.addChild(reqRange);
		reqPerformance.addChild(reqPower);		
				
		// Create shapes for requirements
		IRequirementUIModel reqPowerShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqPower);
		reqPowerShape.setBounds(1195, 442, 97, 40);
		// Hide out the attributes
		// Default (follow diagram setting) = 0
		// Show all attributes = 1
		// Show non-empty only = 2
		// Hide all = 3
		reqPowerShape.setShowAttributes(3);
		reqPowerShape.resetCaption();

		IRequirementUIModel reqHybridSUVShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqHybridSUV);
		reqHybridSUVShape.setBounds(582, 65, 97, 40);
		reqHybridSUVShape.setShowAttributes(3);
		reqHybridSUVShape.resetCaption();
		
		IRequirementUIModel reqLoadShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqLoad);
		reqLoadShape.setBounds(256, 220, 146, 111);
		reqLoadShape.resetCaption();
		
		IRequirementUIModel reqEcoFriendlinessShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqEcoFriendliness);
		reqEcoFriendlinessShape.setBounds(550, 220, 161, 111);
		reqEcoFriendlinessShape.resetCaption();

		IRequirementUIModel reqPerformanceShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqPerformance);
		reqPerformanceShape.setBounds(863, 222, 161, 111);
		reqPerformanceShape.resetCaption();
	
		IRequirementUIModel reqErgonomicsShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqErgonomics);
		reqErgonomicsShape.setBounds(1151, 222, 97, 40);
		reqErgonomicsShape.setShowAttributes(3);
		reqErgonomicsShape.resetCaption();

		IRequirementUIModel reqPassengersShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqPassengers);
		reqPassengersShape.setBounds(159, 442, 97, 40);
		reqPassengersShape.setShowAttributes(3);
		reqPassengersShape.resetCaption();

		IRequirementUIModel reqFuelCapacityShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqFuelCapacity);
		reqFuelCapacityShape.setBounds(266, 504, 97, 40);
		reqFuelCapacityShape.setShowAttributes(3);
		reqFuelCapacityShape.resetCaption();
		
		IRequirementUIModel reqCargoShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqCargo);
		reqCargoShape.setBounds(371, 444, 97, 40);
		reqCargoShape.setShowAttributes(3);
		reqCargoShape.resetCaption();

		IRequirementUIModel reqEmissionsShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqEmissions);
		reqEmissionsShape.setBounds(531, 442, 201, 111);
		reqEmissionsShape.resetCaption();

		IRequirementUIModel reqAccelerationShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqAcceleration);
		reqAccelerationShape.setBounds(810, 444, 97, 40);
		reqAccelerationShape.setShowAttributes(3);
		reqAccelerationShape.resetCaption();
		
		IRequirementUIModel reqFuelEconomyShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqFuelEconomy);
		reqFuelEconomyShape.setBounds(827, 528, 244, 131);
		reqFuelEconomyShape.resetCaption();

		IRequirementUIModel reqBrakingShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqBraking);
		reqBrakingShape.setBounds(985, 444, 97, 40);
		reqBrakingShape.setShowAttributes(3);
		reqBrakingShape.resetCaption();

		IRequirementUIModel reqRangeShape = (IRequirementUIModel) diagramManager.createDiagramElement(reqDiagram, reqRange);
		reqRangeShape.setBounds(1098, 528, 97, 40);
		reqRangeShape.setShowAttributes(3);
		reqRangeShape.resetCaption();
				
		// Create containment relationships
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqHybridSUVShape, reqLoadShape, new Point[] {new Point(630, 105), new Point(630, 165), new Point(329, 165), new Point(329, 220)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqHybridSUVShape, reqEcoFriendlinessShape, new Point[] {new Point(630, 105), new Point(630, 220)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqHybridSUVShape, reqPerformanceShape, new Point[] {new Point(630, 105), new Point(630, 165), new Point(943, 165), new Point(943, 220)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqHybridSUVShape, reqErgonomicsShape, new Point[] {new Point(630, 105), new Point(630, 165), new Point(1199, 165), new Point(1199, 220)});

		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqLoadShape, reqPassengersShape, new Point[] {new Point(329, 331), new Point(329, 386), new Point(207, 386), new Point(207, 442)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqLoadShape, reqFuelCapacityShape, new Point[] {new Point(329, 331), new Point(329, 504)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqLoadShape, reqCargoShape, new Point[] {new Point(329, 331), new Point(329, 386), new Point(419, 386), new Point(419, 444)});

		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqEcoFriendlinessShape, reqEmissionsShape, new Point[] {new Point(630, 331), new Point(630, 442)});

		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqPerformanceShape, reqAccelerationShape, new Point[] {new Point(943, 333), new Point(943, 386), new Point(858, 386), new Point(858, 444)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqPerformanceShape, reqFuelEconomyShape, new Point[] {new Point(943, 333), new Point(943, 528)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqPerformanceShape, reqBrakingShape, new Point[] {new Point(943, 333), new Point(943, 386), new Point(1033, 386), new Point(1033, 444)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqPerformanceShape, reqPowerShape, new Point[] {new Point(943, 333), new Point(943, 386), new Point(1243, 386), new Point(1243, 444)});
		diagramManager.createConnector(reqDiagram, IRequirementDiagramUIModel.SHAPETYPE_CONTAINMENT, reqPerformanceShape, reqRangeShape, new Point[] {new Point(943, 333), new Point(943, 386), new Point(1146, 386), new Point(1146, 528)});
		
		// Show up the diagram
		diagramManager.openDiagram(reqDiagram);

	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
